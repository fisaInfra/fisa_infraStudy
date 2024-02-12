$(document).ready(function () {
    $("#loadComments").click(function () {
        console.log("버튼 클릭됨.");
        $.ajax({
            url: "/comment/read", // 수정된 부분: 상대 경로 대신 절대 경로 사용
            type: "GET",
            data: {boardId: 1},
            dataType: "json",
            success: function (data) {
                console.log(data);
                var commentsContainer = $("#commentsContainer");
                commentsContainer.empty();

                if (data.length > 0) {
                    var table = $("<table>");
                    table.append("<thead><tr><th>댓글 내용</th><th>작성자</th><th>작성일</th></tr></thead>");
                    var tbody = $("<tbody>");

                    $.each(data, function (index, comment) {
                        var row = $("<tr>");
                        row.append("<td>" + comment.content + "</td>");
                        row.append("<td>" + comment.loginId + "</td>");
                        row.append("<td>" + comment.createdAt + "</td>");

                        // 필요한 경우 추가 필드를 여기에 추가

                        tbody.append(row);
                    });

                    table.append(tbody);
                    commentsContainer.append(table);
                } else {
                    commentsContainer.append("<p>No comments available</p>");
                }
            },
            error: function (error) {
                console.error("댓글 로딩 중 에러: " + error.responseText);
            }
        });
    });
});
