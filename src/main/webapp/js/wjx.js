import "./jQuery"
const data = {

};
$.ajax({
    url: "http://baidu.com",
    data: data,
    async: true,
    success: (s) => {
        console.log("success " + s);
    },
    error: (s) => {
        console.log("error: " + s);
    }
});
