// noinspection ES6ConvertVarToLetConst

let i = 0x10000;
setInterval(() => {
    let s = i.toString(16);
    let f1 = f(s);
    console.log(i + " " + s + " " + f1 + " " + eval("\"" + f1 + "\""));
    ++i;
}, 1);

function f(s) {
    // noinspection ES6ConvertVarToLetConst
    var i = parseInt(s, 16) - 0x10000;
    // noinspection ES6ConvertVarToLetConst
    var n1 = (i >> 10) + 0xD800, n2 = (i & 0b1111111111) + 0xDC00;
    return "\\u" + n1.toString(16).toUpperCase() + "\\u" + n2.toString(16).toUpperCase();
}