var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val()
        };

        $.ajax({
            type: 'POST',
            url: '/post',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(res) {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (err) {
            console.log(err);
        });
    }

};

main.init();


/* main이라는 변수의 속성으로 function을 추가한 이유

브라우저의 scope는 공용으로 쓰이기 때문에 나중에 불려진 js의 init, save가 먼저 불려진 js의 function을 덮어쓰게 됨

이런 문제를 피하려고 main.js만의 변수,function 영역으로 var main이란 객체 안에서 function을 선언
이렇게 되면 main 객체 안에서만 이름이 유효하기 때문에 다른 JS와 겹칠 위험이 사라짐
*/