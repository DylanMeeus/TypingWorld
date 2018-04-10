
var url = "http://localhost:8080/register";
var live_url = "https://serene-sea-26357.herokuapp.com/register";

window.onload = function() {
    var registration = new Vue({
        el: "#registration",
        data: {
            username: "",
            password: "",
            hasError: false
        }, methods: {
            submit: function () {
                var self = this;
                var usernameField = document.getElementById("username");
                var pwField = document.getElementById("password");

                var data = new FormData();
                data.set("username", usernameField.value);
                data.set("password", pwField.value);
                fetch(url, {
                    method: "POST",
                    body: data
                }).then(result => result.json())
                    .then(result => {
                        if (result) {
                            // success!
                            self.hasError = false;
                        } else {
                            self.hasError = true;
                            window.location.replace(window.location.href.substring(0,window.location.href.length-"registration.registration.html".length) + "account.html");
                        }
                    });

            }
        }
    })

}