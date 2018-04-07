
// bootstrap app to the loading of the page (it doesn't work as shown in vue their examples)

var url = "http://localhost:8080/words?amount=100";
var live_url = "https://serene-sea-26357.herokuapp.com/words?amount=100";

// setup
var loadedWords = [];
var visibleWords = [];
var visibleWordCount = 10;
var timerStarted = false;
// the wrongly typed words = total keystrokes - count(words.length)

// user based
var wordsTyped = 0;


window.onload = function() {
    var app = new Vue({
        el: "#app",
        data: {
            wordlist: "",
            timer: 2,
            correctlyTypedChars : 0,
            totalTypedChars : 0,
            cpm : 0,
            wpm : 0,

            // ui variables
            resultsVisible: false
        },
        methods: {
            loadWords: function () {
                // Ping the server for some sweet words!
                fetch(url, {
                    headers: {
                        'content-type': 'application/json'
                    },
                    method: "get",
                    mode: "cors"
                }).then(
                    response => response.json()
                ).then(arr =>{
                        // apperantly Jackson or Javascript removes the quotes.
                        loadedWords = arr;
                        visibleWords = loadedWords.slice(wordsTyped, wordsTyped + visibleWordCount);
                        this.wordlist = visibleWords.join(" ");
                    }
                );
            },

            updateUserInput: function() {
                // todo: check each letter of the input box against the ones that need to be typed
                var self = this; // for when we lose the 'this' scope

                // if the last character typed is a space, ignore it!
                var inputfield = document.getElementById("userinput");
                if (inputfield.value[inputfield.value.length - 1] == " ") {
                    inputfield.value = inputfield.value.substring(0, inputfield.value.length - 1);
                    return;
                }

                this.totalTypedChars++;

                if (!timerStarted) {
                    // we start the timer!
                    var interval = window.setInterval(function () {
                        self.timer -= 1;
                        if (self.timer == 0) {
                            clearInterval(interval);
                            self.testFinished();
                        }
                    }, 1000);
                    timerStarted = true;
                }
                // check if the input is equal to the first word
                if (inputfield.value == visibleWords[0]) {
                    wordsTyped += 1;
                    this.correctlyTypedChars += visibleWords[0].length;
                    visibleWords = loadedWords.slice(wordsTyped, wordsTyped + visibleWordCount);
                    this.wordlist = visibleWords.join(" ");
                    inputfield.value = "";
                }
            },

            testFinished : function() {
                // Think of how to show the stats in a better way!

                //calculate cpm, wpm
                this.cpm = this.correctlyTypedChars;
                this.wpm = this.cpm / 5;

            } ,
        },
        beforeMount() {
            this.loadWords();
        }
    });
}