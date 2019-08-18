<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<%
    {
%>
<!--STATUS OK-->
<%
    }
%>
<a ID="page_top"></a>
<%--<%="<!--tomcat页面、引入配置区域，部署到前端会留下空行"%>--%>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="pers.zhc.u.common.Color" %>
<%--
<%!
    private String demo.u.ArrayToString(String[] a) {
        StringBuilder r = new StringBuilder();
        if (a.length > 0) r.append(a[0]);
        if (a.length > 1) {
            for (int i = 1; i < a.length; i++) {
                r.append(",");
                r.append(a[i]);
            }
        }
        return r.toString();
    }
%>
--%>
<%
    String[] un_arr = new String[0], pw_arr = new String[0];
    int l = 0;
    try {
        File f = new File("C:/tomcat_private/login.txt");
        if (!f.exists()) System.out.println(f.createNewFile());
        FileInputStream fis2 = new FileInputStream(f);
        InputStreamReader isr2 = new InputStreamReader(fis2, StandardCharsets.UTF_8);
        BufferedReader br2 = new BufferedReader(isr2);
        List<String> f_r = new ArrayList<>();
        int dvd_i = 0;
        l = 0;
        String r = br2.readLine();
        while (r != null) {
            l++;
            f_r.add(r);
            r = br2.readLine();
        }
        un_arr = new String[l];
        pw_arr = new String[l]/*, email_arr = new String[l]*/;
//    f_r_s = demo.u.ArrayToString(f_r.toArray(new String[0]));
        for (String s : f_r.toArray(new String[0])) {
            un_arr[dvd_i] = s.split("\t")[0];
            pw_arr[dvd_i] = s.split("\t")[1];
//        email_arr[dvd_i] = s.split("\t")[2];
            dvd_i++;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%!
    private File file_() {
        File f = null;
        try {
            f = new File("C:/tomcat_private/t/a.txt");
            if (!f.exists()) {
                System.out.println(f.createNewFile());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return f;
    }

    private File file2() {
        File f = null;
        try {
            f = new File("C:/tomcat_private/t/t.txt");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return f;
    }

    private BufferedReader br2() throws IOException {
        FileInputStream fis = new FileInputStream(file2());
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    private FileOutputStream fos_() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file_(), true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return fos;
    }

    private FileOutputStream fos = fos_();
    private OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
    private BufferedWriter bw = new BufferedWriter(osw);
%>
<%
    long PV = 0L;
    String userBrowser = null;
    String userOS = null;
    int ErrC = 0;
//    StringBuffer requestURL = null;
    try {
//        requestURL = request.getRequestURL();
        String agent = request.getHeader("user-agent");
        Date date = new Date();
        String fD = new SimpleDateFormat("yyMMdd-HHmmss").format(date);
        System.out.println(fD);
        Runtime.getRuntime().exec("C:/tomcat_private/t/t.bat");
        try {
            StringTokenizer st = new StringTokenizer(agent, ";");
            st.nextToken();
            userBrowser = st.nextToken();
            userOS = st.nextToken();
        } catch (Exception e) {
            ErrC++;
        }
        bw.write(fD + "--");
        bw.write(userBrowser + "   ");
        bw.write(userOS + "   " + request.getRemoteHost() + "   " + request.getRemotePort() + "\r\n" + "----------------------------------------------------------" + "\r\n");
        bw.flush();
        BufferedReader br = br2();
        PV = Long.parseLong(br.readLine());
    } catch (Exception e) {
        ErrC++;
    }
%>
<!-- author bczhc0@126.com -->
<html lang="en">
<head>
    <title>zhc</title>
    <%--<link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>--%>
    <link rel="icon" href="_index.svg" sizes="any">
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--    <%
            try {
                /*InputStream scriptRqLenIS
                        = new URL(*//*Objects.requireNonNull(requestURL) + *//*   "http://localhost/js?rqL=true").openConnection().getInputStream();
                InputStreamReader isr = new InputStreamReader(scriptRqLenIS, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                int a = Integer.valueOf(br.readLine());*/
                /*for (int i = 0; i < 5; i++) {
                    out.println(String.format((i == 0 ? "" : "\t") + "<script src=\"js?a=%d\"></script>", i));
                }*/
            } catch (Exception ignored) {
                ErrC++;
            }

        %>--%>
    <script src="js/jQuery/jquery-1.12.4.js"></script>
    <script src="js/jQuery/qrcode/jquery-1.10.2.min.js"></script>
    <script src="js/vue/vue.js"></script>
    <script src="js/func.js"></script>
    <script src="js/pi.js"></script>
</head>
<body>
<div id="Main">
    <p>文字拆分：</p>
    <label for="templet">模板：</label><input type="text" id="templet" title=""/><br/>
    <label for="text">文字：</label><input type="text" id="text" title=""/><br/>
    <div id="o"></div>
    <div class="hrD"></div>
    <p>大数乘方</p>
    <form name="thisform" method="post" action="2.htm">
        <label for="ipt1">底数：</label><input type="text" name="base" title="" class="a" id="ipt1"/><br/>
        <label for="ipt2">指数：</label><input type="text" name="exponent" class="a" title="" id="ipt2"/><br/>
        <input type="submit" name="sbm" title="" id="sbm" value="计算"/>
    </form>
    <div class="hrD"></div>
    <p>模拟登录验证：</p>
    <%
        boolean pass = false, haveLogin = false;
    %>
    <%
        try {
            String username = request.getParameter("un");
            String password = request.getParameter("pw");
            if (username != null && password != null) {
                haveLogin = true;
                System.out.println(l);
                for (int i = 0; i < l; i++) {
                    if (username.equals(new String(un_arr[i].getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))
                            && password.equals(new String(pw_arr[i].getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))) {
                        pass = true;
                    }
                }
            }
    %>
    <span style="color: <%
                if (pass) out.print("limegreen");
                else out.print("red");
                %>"><%
            if (pass) out.print("验证通过！");
            else if (haveLogin) out.print("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            ErrC++;
        }
    %></span>
    <form action="" method="post" name="form1" v-bloak="">
        <label>
            用户名:
            <input type="text" title="" name="un" class="demo.u.b">
        </label><br/>
        <label>
            密{{spcL}}码:
            <input type="password" title="" name="pw" class="demo.u.b">
        </label><br/>
        <input type="submit" value="验证" id="lo">
        <a target="_blank" href="8.htm" class="register" id="register">点击注册</a>
    </form>
    <div class="hrD"></div>
    <div id="tU_btn_div">
        <button id="tU_btn">打开/关闭 上传文本</button>
    </div>
    <div style="display: none" id="tU_div">
        <form action="5.htm" name="wUpload" method="post" v-bloak="">
            <label for="tU"></label><textarea name="tU" id="tU" cols="100" rows="30" title=""></textarea><br/>
            <label>
                文本扩展名：*.
                <input type="text" title="" name="fX" style="width: 40px"/>
            </label>{{spc}}{{spc}}
            <input type="submit" title="" name="tU_s">(编码为UTF-8)
        </form>
    </div>
    <div class="hrD"></div>
    <p>生成随机数：</p>
    <form action="6.htm" name="ran" method="get" v-bloak="">
        <label>
            最{{spcL}}小{{spcL}}值：
            <input type="text" title="" name="ran_min"/>
        </label><br/>
        <label>
            最{{spcL}}大{{spcL}}值：
            <input type="text" title="" name="ran_max"/>
        </label><br/>
        <label for="">生成随机数数量：</label><label for=""></label><input type="text" name="numbers" id="" title=""><br/>
        <label for="ran_repetition"></label><label for="ran_repetition"></label><select name="repetition"
                                                                                        id="ran_repetition" title="">
        <option value="0" name="repetition_true">随便</option>
        <option value="1" name="repetition_false">唯一</option>
    </select>
        <input type="submit" title="" name="ran_sbm">
    </form>
    <div class="hrD"></div>
    <form action="7.htm" method="post" name="msg">
        <label>
            <input type="text" title="" name="msg_i"/>
        </label>
        <input type="submit" title="" name="msg_sbm" value="留言"/>
    </form>
    <div class="hrD"></div>
    <input type="file" id="fUp"/>
    <button onclick="fUpl()">提交</button>
    <div class="hrD"></div>
    查询QQ昵称：<label>
    <input type="number" id="qqNum"/>
</label>
    <div id="qqNameO"></div>
    <div class="hrD"></div>
    Base64编、解码：<br/>
    <label for="base64I"></label><textarea name="" id="base64I" cols="30" rows="10"></textarea>
    <label for="base64Select"></label>
    <select name="" id="base64Select">
        <option value="encode">编码</option>
        <option value="decode">解码</option>
        <option value="both">两者</option>
    </select>
    编码：<label>
    <input type="text" id="base64Charset" value="UTF-8"/>
</label>
    <br/>
    <div id="base64R"></div>
    <div class="hrD"></div>
    <a href="chat">网页聊天</a>
    <div class="hrD"></div>
    <a href="FileUpload.do">上传文件并获取MD5和Base128编码后文件</a>
    <div class="hrD"></div>
    人工智障聊天API：（api来自[QQ昵称为‘失控的疯子’]）
    <form action="javascript:void(0);">
        <label for="chat_api_i"></label><input type="text" id="chat_api_i"/>
        <input type="submit" onclick="{
        _('#chat_api_o').innerHTML = '正在请求……';
        $.ajax({
            url: 's/crazyAPI.do',
            data: {
                msg: _('#chat_api_i').value
            },
            success: function (data){
                _('#chat_api_o').innerHTML = data;
            },
            error: function (data){
                console.log('err: ' + data);
            }
        });
    }">
    </form>
    <div id="chat_api_o"></div>
    <div class="hrD"></div>
    <form action="javascript:void(0);">
        古诗搜索：
        <label for="poetry_i"></label><input type="text" id="poetry_i"/>
        <input type="submit" id="poetry_sbm"/>
    </form>
    <div id="poetry_o"></div>
    <div class="hrD"></div>
    文章：
    <br/>
    <a href="pdf/pdf.pdf?a=0">反迷你教程</a>
    <br>
    <a href="pdf/pdf.pdf?a=1">Thinking in Java</a>
    <div class="hrD"></div>
    <a id="natappB_a" style="color: #000;" href="u/natapp/natapp.do">natapp</a>: <a href="javascript:void(0);"
                                                                                    id="natapp_a"
                                                                                    style="color: green; text-decoration-line: none">
    <span id="natappO"></span></a><br/>
    生成π：<br/>
    <form action="javascript:void(0);">
        <label>
            位数：<input type="number" style="width: 50px;" id="piI">
            <input type="submit" value="生成" id="pi_sbm">
        </label>
    </form>
    <div id="piO"></div>
    <div id="accessCount">PV: <%=PV%>
    </div>
    <a href="javascript:void(0);" onclick="(() => {
    document.write(w);
    window.location.href += '#page_top';
})()">本页用到的JavaScript</a><br>
    <span><%= ErrC > 0 ? "The page has " + ErrC + " error(s)" : "" %></span>
    <div class="hrD"></div>
    <%--    <img src="s/sc.do" alt=""><br>--%>
    <%=request.getSession().getId()%>
    <object data="_index.svg" id="svgO" style="display: none;"></object>
    <br>
</div>
</body>
<style>
    <!--

    /*hr {
        background-color: #d008d9;
        color: #d008d9
    }*/

    div.hrD {
        width: 100%;
        height: 2px;
        margin: 5px;
    }

    audio {
        width: 100%;
        display: block;
    }

    .register:link {
        color: #0000EE;
        text-decoration: none;
    }

    .register:visited {
        color: #0000EE;
        text-decoration: none;
    }

    .register:hover {
        color: #0000EE;
        text-decoration: none;
        border-bottom: solid 1px #0000EE;
    }

    .register:active {
        color: red;
        text-decoration: none;
    }

    input[name="ran_min"], input[name="ran_max"] {
        margin: auto auto auto 27px;
    }

    input [type="password"] [title=""] [name="pw"] [class="demo.u.b"] {
        margin: auto auto auto 0;
    }

    form[name="form1"] label input {
        border-radius: 5px;
    }

    [v-bloak] {
        display: none;
    }
    -->
</style>
<script>
    <!--
    let base64Active = function () {
        switch (_("#base64Select").value) {
            case "encode":
                $.ajax({
                    url: "11.htm",
                    data: {
                        s: _("#base64I").value,
                        charset: _("#base64Charset").value,
                        l: "true"
                    },
                    success: function (data) {
                        _("#base64R").innerHTML = data;
                        _("#base64I").style.backgroundColor = "#b9ffa7";
                    },
                    error: function (data) {
                        console.log(data);
                        _("#base64I").style.backgroundColor = "#ff7869";
                    }
                });
                break;
            case "decode":
                $.ajax({
                    url: "11.htm",
                    data: {
                        base64: _("#base64I").value,
                        charset: _("#base64Charset").value,
                        l: "true"
                    },
                    success: function (data) {
                        _("#base64R").innerHTML = data;
                        _("#base64I").style.backgroundColor = "#b9ffa7";
                    },
                    error: function (data) {
                        console.log(data);
                        _("#base64I").style.backgroundColor = "#ff7869";
                    }
                });
                break;
            default:
                $.ajax({
                    url: "11.htm",
                    data: {
                        s: _("#base64I").value,
                        base64: _("#base64I").value,
                        charset: _("#base64Charset").value,
                        l: "true"
                    },
                    success: function (data) {
                        _("#base64R").innerHTML = data.replace(/\n/g, "<br/>");
                        _("#base64I").style.backgroundColor = "#b9ffa7";
                    },
                    error: function (data) {
                        console.log(data);
                        _("#base64I").style.backgroundColor = "#ff7869";
                    }
                });
                break;
        }
    };


    let natappAjaxDo = () => {
        $.ajax({
            url: "u/natapp/natapp.do",
            async: true,
            success: function (data) {
                let s = data;
                let r = "";
                for (let i = 0; i < s.length; i++) {
                    if (s[i] !== "\\") {
                        r += s[i];
                    } else break;
                }
                try {
                    let r_ = r.match(/http:\/\/.*\.natappfree\.cc/)[0];
                    try {
                        _("#natappO").innerHTML = (r_ === "" ? null : r_);
                        if (!(r_ === null || window.location.href.match(/.*natappfree\.cc.*/) || window.location.href
                            .match(/.*localhost.*/) || window.location.href.match(/.*192\.168\..*/))) {
                            window.location.href = r_;
                        }
                    } catch (ignored) {
                    }
                } catch (e) {
                    _("#natappO").innerHTML = "Error " + data + " natapp未开启";
                }
            },
            error: function (data) {
                _("#natappO").innerHTML = "Error" + data + " natapp未开启";
                $(_("#natappO")).css({
                    color: "red"
                });
            }
        });
    };
    //todo jqE
    $(function () {
        let ranColor = "<%=new Color().ranColor()%>";
        $(".hrD").css({
            backgroundColor: ranColor
        });

        $(".a").attr({
            placeholder: "请输入数字"
        });

        $(_("#natappO")).css({
            color: "green"
        });

        _("#pi_sbm").onclick = () => {
            _("#piO").innerHTML = pi(Number(_("#piI").value));
        };

        natappAjaxDo();

        $("#poetry_sbm").on({
            click: () => {
                _("#poetry_o").innerHTML = "正在请求……";
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "s/Poetry.do",
                    data: {
                        name: $("#poetry_i").val()
                    },
                    success: function (data) {
                        console.log("ok");
                        let r = data.result;
                        let h = "";
                        for (let i = 0; i < r.length; i++) {
                            let z = r[i];
                            h += z.title + "&nbsp;&nbsp;&nbsp;" + z.content.replace(/\|/g, "") + "&nbsp;&nbsp;&nbsp;" + z["authors"] + "<br/><br/>";
                        }
                        $("#poetry_o").html(h);
                    },
                    error: function (data) {
                        alert("PoetryPostError: " + data);
                    }
                });
            }
        });

        $("#qqNum").on({
            keyup: function () {
                let qqN = "";
                let v = $("#qqNum").val();
                if (v.length >= 5 && v !== "10000") qqN = v;
                $.ajax({
                    type: "POST",
                    url: "12.htm",
                    data: {
                        qqN: qqN
                    },
                    dataType: "text",
                    success: function (data) {
                        if (v.length >= 5 && v !== "10000") {
                            _("#qqNameO").innerHTML = data;
                            console.log(qqN + "\t" + data);
                        }
                    },
                    error: function (data) {
                        console.log("error\t" + data);
                    }
                });
            }
        });
        let HiddenClick = 0;
        $("#accessCount").on({
            click: function () {
                HiddenClick++;
                if (HiddenClick === 5) {
                    location.href = "p";
                }
            }
        });
        $("#base64I").on({
            keyup: base64Active,
            click: base64Active,
            blur: base64Active,
            focus: base64Active
        });
        $("#base64Charset").on({
            blur: base64Active
        });
        $("#base64Select").on({
            change: base64Active,
            blur: base64Active
        });
        $("#natappB_a").on({
            click: natappAjaxDo
        });
        $("#natapp_a").on({
            click: () => {
                natappAjaxDo();
                if (/.*natapp.*\.cc.*/.test(_("#natappO").innerHTML)) {
                    window.location.href = _("#natappO").innerHTML;
                }
            }
        });
        $("#text, #templet").on({
            keyup: function () {
                let templet = _("#templet").value;
                let text = _("#text").value;
                _("#o").innerHTML = SuperSplit(templet, text).join("<br/><br/>").replace(/ /gi, "&nbsp;");
            }
        });
        _("#tU_btn").onclick = function () {
            $("#tU_div").slideToggle();
        };
    });
    window.onload = function () {
        _("#lo").onclick = function () {
            let un = document.form1.un;
            let pw = document.form1.pw;
            if (!(un.value === "" && pw.value === "")) {
                if (un.value === "") {
                    alert("请输入用户名");
                    un.focus();
                    return false;
                } else if (pw.value === "") {
                    alert("请输入密码");
                    pw.focus();
                    return false;
                }
            } else {
                alert("请输入用户名和密码");
                un.focus();
                return false;
            }
        };
        let innerWidth = window.innerWidth;
        _("#svgO").style.width = innerWidth + "px";
        _("#svgO").style.height = innerWidth / 1920 * 1080 + "px";
        _("#svgO").style.display = "block";
        setTimeout(() => {
            $("*[v-bloak]").css({
                display: "block"
            });
        }, 1500)
    };
    <%
        if (pass) {out.print("alert(\"验证通过！\");");}
    %>
    <%
    File unknown_f = new File("C:/tomcat_Server/web/____________.txt");
    boolean UnknownNotExist = false;
    if (!unknown_f.exists()) {
        UnknownNotExist = true;
    }
    %>

    if ("<%=UnknownNotExist%>" === "true") {
        if (/Coolpad/i.test("<%=userBrowser%>") || /Coolpad/i.test("<%=userOS%>")) {
            let pmpt;
            for (; ;) {
                pmpt = prompt("贵姓？");
                if (pmpt !== "" && pmpt !== null) break;
            }
            window.location.href = "9.htm?s=" + encodeURI(pmpt);
        }
    }

    function fUpl() {
        const f = _("#fUp");
        if (f.files.length === 0) return;
        const fName = f.files[0].name;
        let reader = new FileReader();
        reader.onload = function (e) {
            $.ajax({
                type: "POST",
                url: "10.htm",
                data: {
                    base64: e.target["result"],
                    fName: fName
                },
                dataType: "text",
                traditional: true,
                success: function () {
                    alert("成功");
                },
                error: function () {
                    alert("error");
                },
            });
            console.log(f.files[0].name);
            console.log(e.target["result"]);
        };
        reader.readAsDataURL(f.files[0]);
    }

    const w = '<a target="_blank" href="js/func.js">func.js</a><br/>\n<a target="_blank" href="js/jQuery/jquery-1.12.4.js">jQuery-1.12.4.js' +
        '</a><br/>\n<a target="_blank" href="js/jQuery/qrcode/jquery.qrcode.min.js">qrcode.js</a><br/>\n<a href="js/vue/vue.js" target="_blank">Vue.js</a><br/>\n<a target="_blank" href="javascript:' +
        'window.location.href = window.location.href.match(/.*[#]/)[0]\n.substr(0, window.location.href.match(/.*[#]/)[0].length - 2)">返回</a>';

    new Vue({
        el: "#Main",
        data: {
            spc: "\u0020",
            spcL: "\u2003"
        }
    });
    -->
</script>
</html>
<%
%>