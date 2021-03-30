let targetId;

// HTML 문서를 로드할 때마다 실행합니다.
$(document).ready(function () {
    getMessages();
})

// 메모를 불러와서 보여줍니다
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#postings').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/memos',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let message = response[i];
                let id = message['memoId'];
                let title = message['title']
                let usernameId = message['usernameId'];
                let contents = message['contents'];
                let modifiedAt = message['modifiedAt'];
                addHTML(id, title, usernameId, contents, modifiedAt);
            }
        }
    })
}

// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, title, usernameId, contents, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `
                <tr onclick="getEachMessages(${id})">
                    <th scope="row"> ${title} </th>
                    <td>${usernameId}</td>
                    <td>${modifiedAt}</td>
                </tr>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#postings').append(tempHtml);
}

// 메모를 생성합니다.
function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let title = $('#memo-title').val();
    let contents = $('#memo-contents').val();

    // // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    // if (isValidContents(title) == false) {
    //     return;
    // }
    // if (isValidUsername(username) == false) {
    //     return;
    // }
    // if (isValidContents(contents) == false) {
    //     return;
    // }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'title': title, 'contents': contents};

    // 4. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/api/memos",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.href='/';
        }
    });
}

// 개별 게시글을 보여줍니다
function getEachMessages(id) {
    window.location.href = `/post/read/${id}`
}

// 댓글을 작성합니다.
function writeComment() {
    let comment = $('#comment').val();
    let data = {'content': comment}

    $.ajax({
        type: "POST",
        url: window.location.pathname +"/comment",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('댓글이 성공적으로 작성되었습니다.');
            location.reload();
        }
    });
}

