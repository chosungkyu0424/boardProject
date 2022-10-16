'use strict';

let index = {
    init: function () {
        $("#btn-save").on("click", () => { //this를 바인딩하기 위해 화샬표 함수 사용
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원가입 안됨")
            } else {
                this.save();
            }
        });
        $("#btn-update").on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() == false) {
                console.log("회원수정 안됨")
            } else {
                this.update();
            }
        });
    },

    save: function () {
        let data = { //JavaScript Object
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            nickname: $("#nickname").val()
        }

        $.ajax({
            type: "POST", //Http method
            url: "/auth/api/v1/user", //API 주소
            data: JSON.stringify(data), //JSON으로 변환 //http body 데이터
            contentType: "application/json; charset=utf-8", //MIME 타입(body데이터가 어떤 타입인지)
            dataType: "json" //응답 데이터(서버로 요청을 해서 응답이 왔을때 기본적으로 거의 모든 것이 문자열인데 생긴게 json형태라면 javascript 오브젝트로 변경해준다
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

    update: function () {
        let data = {
            id: $("#id").val(),
            password: $("#password").val(),
            nickname: $("#nickname").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/v1/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }
}
index.init();