
var url = "http://localhost:8080/register";
var live_url = "https://serene-sea-26357.herokuapp.com/register";

window.onload = function() {
    var registration = new Vue({
        el: "#registration",
        data: {
            username: "",
            password: ""
        }, methods: {
            submit: function () {
                var usernameField = document.getElementById("username");
                var pwField = document.getElementById("password");

                var data = new FormData();
                data.set("username", usernameField.value);
                data.set("password", pwField.value);
                fetch(url, {
                    method: "POST",
                    body: data
                }).then(result => console.log(result));

            }
        }
    })

}