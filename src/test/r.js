(function (window) {
    if (window.GS.COMMON !== undefined) {
        return;
    }
    window.GS.COMMON = {};
})(window, undefined);

//工具类
(function (window) {
    var utils = {
        xmloperation: {
            //root format {nodeName:nodeName(must),value:value(optional),attrArray:[{name:name(must),value:value(must)}](optional)}
            createXml: function (root, encoding) {
                if (utils.tools.isEmpty(encoding)) {
                    encoding = "";
                } else {
                    encoding = "encoding=\"" + encoding + "\"";
                }
                var xmlDoc = "<?xml version=\"1.0\" " + encoding + "?>";
                if (!utils.tools.isEmpty(root)) {
                    xmlDoc += utils.xmloperation.createNode(root);
                }
                return xmlDoc;
            },
            //obj format {nodeName:nodeName(must),value:value(optional),attrArray:[{name:name(must),value:value(must)}](optional)}
            createNode: function (obj, isCdata) {
                if (utils.tools.isEmpty(isCdata)) {
                    isCdata = true;
                }
                if (utils.tools.isEmpty(obj)) return;
                if (utils.tools.isEmpty(obj.nodeName)) return;
                var node = "<" + obj.nodeName;
                if (!utils.tools.isEmpty(obj.attrArray)) {
                    for (var i = 0; i < obj.attrArray.length; i++) {
                        var attr = obj.attrArray[i];
                        node += " " + attr.name + "=\"" + attr.value + "\"";
                    }
                }
                var node = node + ">";
                if (!utils.tools.isEmpty(obj.value)) {
                    if (isCdata) {
                        node = node + "<![CDATA[" + obj.value + "]]>";
                    } else {
                        node = node + obj.value;
                    }
                }
                var node = node + "</" + obj.nodeName + ">";
                return node;
            },
            createNodeNone: function (obj) {
                if (utils.tools.isEmpty(obj)) return;
                if (utils.tools.isEmpty(obj.nodeName)) return;
                var node = "<" + obj.nodeName;
                if (!utils.tools.isEmpty(obj.attrArray)) {
                    for (var i = 0; i < obj.attrArray.length; i++) {
                        var attr = obj.attrArray[i];
                        node += " " + attr.name + "=\"" + attr.value + "\"";
                    }
                }
                var node = node + "/>";
                return node;
            },
            createNodeText: function (obj, isCdata) {
                if (utils.tools.isEmpty(isCdata)) {
                    isCdata = true;
                }
                if (utils.tools.isEmpty(obj)) return;
                if (utils.tools.isEmpty(obj.nodeName)) return;
                var node = "<" + obj.nodeName;
                if (!utils.tools.isEmpty(obj.attrArray)) {
                    for (var i = 0; i < obj.attrArray.length; i++) {
                        var attr = obj.attrArray[i];
                        node += " " + attr.name + "=\"" + attr.value + "\"";
                    }
                }
                var node = node + ">";
                if (!utils.tools.isEmpty(obj.value)) {
                    if (isCdata) {
                        node = node + "<![CDATA[" + obj.value + "]]>";
                    } else {
                        node = node + obj.value;
                    }
                }
                if (!utils.tools.isEmpty(obj.textArray)) {
                    if (obj.textArray.length > 0) {
                        for (var i = 0; i < obj.textArray.length; i++) {
                            var textObj = obj.textArray[i];
                            if (!utils.tools.isEmpty(textObj.nodeName)) {
                                node = node + "<" + textObj.nodeName + "><![CDATA[";
                                if (!utils.tools.isEmpty(textObj.value)) {
                                    node = node + textObj.value;
                                }
                                node = node + "]]>" + "</" + textObj.nodeName + ">";
                            }
                        }
                    }
                }
                var node = node + "</" + obj.nodeName + ">";
                return node;
            },
            // parent is parent node, child is child node ,tag the insert label,indexof is the tag  in the parent of order ,from 1;
            addNode: function (parent, child, tag, indexof) {
                var result = 0;
                if (!utils.tools.isEmpty(tag)) {
                    if (utils.tools.isEmpty(indexof)) {
                        indexof = 1;
                    }
                    var cloneStr = parent;
                    var startIndex = 0;
                    for (var i = 0; i < indexof; i++) {
                        startIndex = cloneStr.indexOf(tag, startIndex);
                        if (startIndex == -1) return;
                        var tempIndex = cloneStr.lastIndexOf("<", startIndex);
                        var tempEndTag = cloneStr.lastIndexOf("</", startIndex);
                        if (tempIndex <= tempEndTag) {
                            i--;
                        }
                        startIndex = startIndex + tag.length;
                    }
                    if (startIndex == 0) return;
                    cloneStr = cloneStr.substring(startIndex + tag.length);
                    result += startIndex + tag.length;
                    startIndex = 0;
                    var s = 0;
                    var con = true;
                    while (con) {
                        startIndex = cloneStr.indexOf(tag);
                        if (startIndex == -1) return;
                        var tempIndex = cloneStr.lastIndexOf("<", startIndex);
                        var tempEndTag = cloneStr.lastIndexOf("</", startIndex);
                        if (tempIndex <= tempEndTag) {
                            s--;
                        } else {
                            s++;
                        }
                        if (s < 0) {
                            result += tempEndTag;
                            con = false;
                        }
                    }
                } else {
                    var cloneStr = parent;
                    result = cloneStr.lastIndexOf("</");
                    if (result == -1) return;
                }
                return parent.substring(0, result) + child + parent.substring(result);
            },
            parseXml: function (xml) {
                var xmlDoc;
                if (document.implementation.createDocument) {
                    var parser = new DOMParser();
                    xmlDoc = parser.parseFromString(xml, "application/xml");
                    //IE
                } else if (window.ActiveXObject) {
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(xml);
                }
                xml = xmlDoc;
                return xml
            },
            getXmlNodeAttr: function (node, attrName) {
                if (!node) return "";
                if (!node.attributes) return "";
                if (node.attributes[attrName] != null) return node.attributes[attrName].value;
                if (node.attributes.getNamedItem(attrName) != null) return node.attributes.getNamedItem(attrName).value;
                return "";
            },
            getNodeValue: function (node) {
                var textContent = utils.tools.trim(node.textContent);
                return textContent;
            }
        },
        tools: {
            trim: function (str) {
                if (typeof str !== "string") {
                    return str;
                }
                if (typeof str.trim === "function") {
                    return str.trim();
                } else {
                    return str.replace(/^(\u3000|\s|\t|\u00A0)*|(\u3000|\s|\t|\u00A0)*$/g, "");
                }
            },
            isEmpty: function (obj) {
                if (obj == null || obj == undefined || typeof (obj) == 'undefined' || obj.toString() == 'NaN') {
                    return true;
                } else {
                    return false;
                }
            },
            isBlank: function (obj) {
                if (this.isEmpty(obj) || this.trim(obj) == "") {
                    return true;
                } else {
                    return false;
                }
            },
            isFunction: function (obj) {
                if (this.isEmpty(obj)) {
                    return false;
                }
                if (typeof (obj) == "function") {
                    return true;
                } else {
                    return false;
                }
            },
            isNumber: function (obj) {
                if (this.isEmpty(obj)) {
                    return false;
                }
                var reg = /^([0-9]|[1-9][0-9]*|[0-9]\.[0-9]+|[1-9][0-9]*\.[0-9]+)$/;
                var result = reg.test(obj);
                return result;
            },
            isEmail: function (email) {
                if (this.isBlank(email)) {
                    return false;
                }

                var reg = new RegExp(/^([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[-_.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,6}$/);
                var result = reg.test(email);
                return result;
            },
            isArray: Array.isArray || function (obj) {
                if (typeof obj === 'object') {
                    return Object.prototype.toString.call(obj) === '[object Array]';
                }
                return false;
            },
            urlFileName: function (url) {
                if (!this.isBlank(url)) {
                    var index = url.lastIndexOf("/");
                    if (index > -1) {
                        return url.substring(index + 1);
                    } else {
                        return url;
                    }
                } else {
                    return "";
                }
            },
            fillUrl: function (surl, eurl) {
                surl = this.trim(surl);
                if (surl.indexOf("http://") == 0 || surl.indexOf("https://") == 0) {
                    return surl;
                } else {
                    return this.lastAddress(eurl) + surl;
                }
            },
            lastAddress: function (url) {
                url = this.trim(url);
                var index = url.lastIndexOf("/");
                if (url.lastIndexOf("/") > 0) {
                    return url.substring(0, index + 1);
                } else {
                    return url;
                }
            },
            iosVersion: function () {
                try {
                    var ap = navigator.userAgent;
                    if (/iPhone/i.test(ap) || /iPad/i.test(ap)) {
                        var osInfo = ap.match(/OS ((\d+_?){2,3})\s/);
                        var version = 0;
                        if (osInfo && osInfo.length > 1) {
                            var osVersion = osInfo[1];
                            var index = osVersion.indexOf("_");
                            if (index > 0) {
                                version = osVersion.substr(0, index);
                                if (isNaN(version)) {
                                    version = 0;
                                } else {
                                    version = Number(version);
                                }
                            }
                        }
                        return version;
                    } else {
                        return 0;
                    }
                } catch (e) {
                    return 0;
                }
            },
            analysisRole: function (roleValue) {
                var roleList = "";
                if (roleValue != "") {
                    roleValue = roleValue - 0;
                    if (roleValue % 2 == 1) {
                        roleList = roleList + "1,";
                        roleValue = roleValue - 1;
                    }
                    if (roleValue % 4 == 2) {
                        roleList = roleList + "2,";
                        roleValue = roleValue - 2;
                    }
                    if (roleValue % 8 == 4) {
                        roleList = roleList + "4,";
                        roleValue = roleValue - 4;
                    }
                    if (roleValue % 16 == 8) {
                        roleList = roleList + "8,";
                        roleValue = roleValue - 8;
                    }
                    if (roleValue - 16 == 0) {
                        roleList = roleList + "16,";
                    }
                    if (roleList.length > 0) {
                        roleList = roleList.substring(0, roleList.length - 1);
                    }
                }
                return roleList;
            },
            ajax: function (params) {
                params = params || {};
                if (params.dataType && params.dataType == 'jsonp') {
                    utils.tools.jsonp(params);
                } else {
                    utils.tools.json(params);
                }
            },
            json: function (params) {
                params.data = params.data || {};
                params.type = (params.type || 'GET').toUpperCase();
                params.async = params.async || true;
                var xhr = null;
                if (window.XMLHttpRequest) {
                    xhr = new XMLHttpRequest();
                } else {
                    xhr = new ActiveXObjcet('Microsoft.XMLHTTP');
                }
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        var status = xhr.status;
                        if (status >= 200 && status < 300) {
                            var response = '';
                            var type = xhr.getResponseHeader('Content-type');
                            if (!utils.tools.isEmpty(type) && type.indexOf('xml') !== -1 && xhr.responseXML) {
                                response = xhr.responseXML; //Document对象响应
                            } else if (!utils.tools.isEmpty(type) && type === 'application/json') {
                                response = JSON.parse(xhr.responseText); //JSON响应
                            } else if (!utils.tools.isEmpty(type) && type === 'application/x-javascript') {
                                eval("response =" + xhr.responseText); //JS响应
                            } else {
                                response = xhr.responseText; //字符串响应
                            }
                            params.success && params.success(response);
                        } else {
                            params.error && params.error(status);
                        }
                    }
                    ;
                };
                if (params.type == 'GET') {
                    params.data = this.formatParams(params.data);
                    // 三个参数：请求方式、请求地址(get方式时，传输数据是加在地址后的)、是否异步请求(同步请求的情况极少)；
                    xhr.open(params.type, params.url + '?' + params.data, params.async);
                    xhr.send(null);
                } else {
                    xhr.open(params.type, params.url, params.async);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    xhr.send(params.data);
                }
            },
            jsonp: function (params) {
                var xhr = {status: 500};
                if (params.data && typeof (params.data) == "object" && Object.prototype.toString.call(params.data).toLowerCase() == "[object object]" && !params.data.length) {
                    var statusText = 'unparsererror';
                } else {
                    var statusText = 'parsererror';
                }
                params.data = params.data || {};
                params.async = params.async || true;
                params.data = this.formatParams(params.data);
                var grobalName = "__GSVJsonp";
                var jsonp = window[grobalName] = window[grobalName] || {};
                if (jsonp.N) {
                    jsonp.N++;
                } else {
                    jsonp.N = 1;
                }
                var callbackName = "jsonpcallback_" + jsonp.N;
                var head = document.getElementsByTagName('head')[0];
                var script = document.createElement('script');
                head.appendChild(script);
                script.src = params.url + '?' + params.data;
                script.charset = "UTF-8";
                script.async = params.async;

                jsonp[callbackName] = function (json) {
                    xhr.status = 200;
                    head.removeChild(script);
                    clearTimeout(script[callbackName]);
                    jsonp[callbackName] = null;
                    params.success && params.success(json);
                };
                if (this.IE) {
                    script.onreadystatechange = function () {
                        if (script.readyState.toLowerCase() == "complete" || script.readyState.toLowerCase() == "loaded") {
                            jsonp[callbackName].call(jsonp[callbackName]);
                        } else {
                            params.error && params.error(script.readyState);
                        }
                    };
                } else {
                    script.onload = function () {
                        jsonp[callbackName].call(jsonp[callbackName]);
                    };
                    script.onerror = function (err) {
                        params.error && params.error(err);
                    };
                }
                if (params.time) {
                    script[callbackName] = setTimeout(function () {
                        jsonp[callbackName] = null;
                        head.removeChild(script);
                        params.error && params.error(xhr.status, statusText);
                    }, params.time);
                }
            },
            formatParams: function (data) {
                var arr = [];
                for (var name in data) {
                    arr.push(encodeURIComponent(name) + "=" + encodeURIComponent(data[name]));
                }
                arr.push(("t=" + Math.random()).replace(".", ""));
                return arr.join("&");
            },
            formatXml: function (data) {
                var xmlDoc;
                if (document.implementation.createDocument) {
                    var parser = new DOMParser();
                    xmlDoc = parser.parseFromString(data, "application/xml");
                    //IE
                } else if (window.ActiveXObject) {
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(data)
                }
                return xmlDoc;
            },
            replace: function (target, replaceArray) {
                for (var i = 0; i < replaceArray.length; i++) {
                    var replaceObj = replaceArray[i];
                    target = target.replace(replaceObj[0], replaceObj[1]);
                }
                return target;
            },
            insertString: function (src, position, item) {
                var st1 = src.substring(0, position);
                var st2 = src.substring(position);

                return st1 + item + st2;
            }
        }
    }
    window.GS.COMMON.utils = utils;
})(window, undefined);

//MD5
(function (window) {
    /*
     * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
     * Digest Algorithm, as defined in RFC 1321.
     * Version 2.1 Copyright (C) Paul Johnston 1999 - 2002.
     * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
     * Distributed under the BSD License
     * See http://pajhome.org.uk/crypt/md5 for more info.
     */

    /*
     * Configurable variables. You may need to tweak these to be compatible with
     * the server-side, but the defaults work in most cases.
     */

    var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
    var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance   */
    var chrsz = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */

    /*
     * These are the functions you'll usually want to call
     * They take string arguments and return either hex or base-64 encoded strings
     */
    var MD5 = {
        hex_md5: function (s) {
            return binl2hex(core_md5(str2binl(s), s.length * chrsz));
        },
        b64_md5: function (s) {
            return binl2b64(core_md5(str2binl(s), s.length * chrsz));
        },
        str_md5: function (s) {
            return binl2str(core_md5(str2binl(s), s.length * chrsz));
        },
        hex_hmac_md5: function (key, data) {
            return binl2hex(core_hmac_md5(key, data));
        },
        b64_hmac_md5: function (key, data) {
            return binl2b64(core_hmac_md5(key, data));
        },
        str_hmac_md5: function (key, data) {
            return binl2str(core_hmac_md5(key, data));
        }
    }

    /*
     * Calculate the MD5 of an array of little-endian words, and a bit length
     */
    function core_md5(x, len) {
        /* append padding */
        x[len >> 5] |= 0x80 << ((len) % 32);
        x[(((len + 64) >>> 9) << 4) + 14] = len;

        var a = 1732584193;
        var b = -271733879;
        var c = -1732584194;
        var d = 271733878;

        for (var i = 0; i < x.length; i += 16) {
            var olda = a;
            var oldb = b;
            var oldc = c;
            var oldd = d;

            a = md5_ff(a, b, c, d, x[i + 0], 7, -680876936);
            d = md5_ff(d, a, b, c, x[i + 1], 12, -389564586);
            c = md5_ff(c, d, a, b, x[i + 2], 17, 606105819);
            b = md5_ff(b, c, d, a, x[i + 3], 22, -1044525330);
            a = md5_ff(a, b, c, d, x[i + 4], 7, -176418897);
            d = md5_ff(d, a, b, c, x[i + 5], 12, 1200080426);
            c = md5_ff(c, d, a, b, x[i + 6], 17, -1473231341);
            b = md5_ff(b, c, d, a, x[i + 7], 22, -45705983);
            a = md5_ff(a, b, c, d, x[i + 8], 7, 1770035416);
            d = md5_ff(d, a, b, c, x[i + 9], 12, -1958414417);
            c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
            b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
            a = md5_ff(a, b, c, d, x[i + 12], 7, 1804603682);
            d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
            c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
            b = md5_ff(b, c, d, a, x[i + 15], 22, 1236535329);

            a = md5_gg(a, b, c, d, x[i + 1], 5, -165796510);
            d = md5_gg(d, a, b, c, x[i + 6], 9, -1069501632);
            c = md5_gg(c, d, a, b, x[i + 11], 14, 643717713);
            b = md5_gg(b, c, d, a, x[i + 0], 20, -373897302);
            a = md5_gg(a, b, c, d, x[i + 5], 5, -701558691);
            d = md5_gg(d, a, b, c, x[i + 10], 9, 38016083);
            c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
            b = md5_gg(b, c, d, a, x[i + 4], 20, -405537848);
            a = md5_gg(a, b, c, d, x[i + 9], 5, 568446438);
            d = md5_gg(d, a, b, c, x[i + 14], 9, -1019803690);
            c = md5_gg(c, d, a, b, x[i + 3], 14, -187363961);
            b = md5_gg(b, c, d, a, x[i + 8], 20, 1163531501);
            a = md5_gg(a, b, c, d, x[i + 13], 5, -1444681467);
            d = md5_gg(d, a, b, c, x[i + 2], 9, -51403784);
            c = md5_gg(c, d, a, b, x[i + 7], 14, 1735328473);
            b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

            a = md5_hh(a, b, c, d, x[i + 5], 4, -378558);
            d = md5_hh(d, a, b, c, x[i + 8], 11, -2022574463);
            c = md5_hh(c, d, a, b, x[i + 11], 16, 1839030562);
            b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
            a = md5_hh(a, b, c, d, x[i + 1], 4, -1530992060);
            d = md5_hh(d, a, b, c, x[i + 4], 11, 1272893353);
            c = md5_hh(c, d, a, b, x[i + 7], 16, -155497632);
            b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
            a = md5_hh(a, b, c, d, x[i + 13], 4, 681279174);
            d = md5_hh(d, a, b, c, x[i + 0], 11, -358537222);
            c = md5_hh(c, d, a, b, x[i + 3], 16, -722521979);
            b = md5_hh(b, c, d, a, x[i + 6], 23, 76029189);
            a = md5_hh(a, b, c, d, x[i + 9], 4, -640364487);
            d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
            c = md5_hh(c, d, a, b, x[i + 15], 16, 530742520);
            b = md5_hh(b, c, d, a, x[i + 2], 23, -995338651);

            a = md5_ii(a, b, c, d, x[i + 0], 6, -198630844);
            d = md5_ii(d, a, b, c, x[i + 7], 10, 1126891415);
            c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
            b = md5_ii(b, c, d, a, x[i + 5], 21, -57434055);
            a = md5_ii(a, b, c, d, x[i + 12], 6, 1700485571);
            d = md5_ii(d, a, b, c, x[i + 3], 10, -1894986606);
            c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
            b = md5_ii(b, c, d, a, x[i + 1], 21, -2054922799);
            a = md5_ii(a, b, c, d, x[i + 8], 6, 1873313359);
            d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
            c = md5_ii(c, d, a, b, x[i + 6], 15, -1560198380);
            b = md5_ii(b, c, d, a, x[i + 13], 21, 1309151649);
            a = md5_ii(a, b, c, d, x[i + 4], 6, -145523070);
            d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
            c = md5_ii(c, d, a, b, x[i + 2], 15, 718787259);
            b = md5_ii(b, c, d, a, x[i + 9], 21, -343485551);

            a = safe_add(a, olda);
            b = safe_add(b, oldb);
            c = safe_add(c, oldc);
            d = safe_add(d, oldd);
        }

        return Array(a, b, c, d);

    }

    /*
     * These functions implement the four basic operations the algorithm uses.
     */
    function md5_cmn(q, a, b, x, s, t) {
        return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
    }

    function md5_ff(a, b, c, d, x, s, t) {
        return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
    }

    function md5_gg(a, b, c, d, x, s, t) {
        return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
    }

    function md5_hh(a, b, c, d, x, s, t) {
        return md5_cmn(b ^ c ^ d, a, b, x, s, t);
    }

    function md5_ii(a, b, c, d, x, s, t) {
        return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
    }

    /*
     * Calculate the HMAC-MD5, of a key and some data
     */
    function core_hmac_md5(key, data) {
        var bkey = str2binl(key);
        if (bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);

        var ipad = Array(16), opad = Array(16);
        for (var i = 0; i < 16; i++) {
            ipad[i] = bkey[i] ^ 0x36363636;
            opad[i] = bkey[i] ^ 0x5C5C5C5C;
        }

        var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
        return core_md5(opad.concat(hash), 512 + 128);
    }

    /*
     * Add integers, wrapping at 2^32. This uses 16-bit operations internally
     * to work around bugs in some JS interpreters.
     */
    function safe_add(x, y) {
        var lsw = (x & 0xFFFF) + (y & 0xFFFF);
        var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
        return (msw << 16) | (lsw & 0xFFFF);
    }

    /*
     * Bitwise rotate a 32-bit number to the left.
     */
    function bit_rol(num, cnt) {
        return (num << cnt) | (num >>> (32 - cnt));
    }

    /*
     * Convert a string to an array of little-endian words
     * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
     */
    function str2binl(str) {
        var bin = Array();
        var mask = (1 << chrsz) - 1;
        for (var i = 0; i < str.length * chrsz; i += chrsz)
            bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (i % 32);

        return bin;
    }

    /*
     * Convert an array of little-endian words to a string
     */
    function binl2str(bin) {
        var str = "";
        var mask = (1 << chrsz) - 1;
        for (var i = 0; i < bin.length * 32; i += chrsz)
            str += String.fromCharCode((bin[i >> 5] >>> (i % 32)) & mask);
        return str;
    }

    /*
     * Convert an array of little-endian words to a hex string.
     */
    function binl2hex(binarray) {
        var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
        var str = "";
        for (var i = 0; i < binarray.length * 4; i++) {
            str += hex_tab.charAt((binarray[i >> 2] >> ((i % 4) * 8 + 4)) & 0xF) +
                hex_tab.charAt((binarray[i >> 2] >> ((i % 4) * 8)) & 0xF);
        }

        return str;
    }

    /*
     * Convert an array of little-endian words to a base-64 string
     */
    function binl2b64(binarray) {
        var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        var str = "";
        for (var i = 0; i < binarray.length * 4; i += 3) {
            var triplet = (((binarray[i >> 2] >> 8 * (i % 4)) & 0xFF) << 16)
                | (((binarray[i + 1 >> 2] >> 8 * ((i + 1) % 4)) & 0xFF) << 8)
                | ((binarray[i + 2 >> 2] >> 8 * ((i + 2) % 4)) & 0xFF);
            for (var j = 0; j < 4; j++) {
                if (i * 8 + j * 6 > binarray.length * 32) str += b64pad;
                else str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
            }
        }
        return str;
    }

    window.GS.COMMON.MD5 = MD5;
})(window, undefined);

//表情
(function (window) {
    var emotions = {
        language: 'zh_CN',
        configs: {
            "prefix": "http://static.gensee.com/webcast",
            "list": [
                {
                    "icon": "emotion\\chat.gift.png",
                    "url": "/static/emotion/chat.gift.png",
                    "zh_CN": "【礼物】",
                    "en": "[gift]",
                    "type": 3,
                    "zh_TW": "【禮物】",
                    "ja": "【ギフト】",
                    "st": "$^lw^"
                }
                , {
                    "icon": "emotion\\feedback.agreed.png",
                    "url": "/static/emotion/feedback.agreed.png",
                    "zh_CN": "【赞同】",
                    "en": "[agree]",
                    "type": 1,
                    "zh_TW": "【贊同】",
                    "ja": "【承認】",
                    "st": "$^zt^"
                }
                , {
                    "icon": "emotion\\feedback.applaud.png",
                    "url": "/static/emotion/feedback.applaud.png",
                    "zh_CN": "【鼓掌】",
                    "en": "[clap]",
                    "type": 1,
                    "zh_TW": "【鼓掌】",
                    "ja": "【クラップ】",
                    "st": "$^gz^"
                }
                , {
                    "icon": "emotion\\feedback.quickly.png",
                    "url": "/static/emotion/feedback.quickly.png",
                    "zh_CN": "【太快了】",
                    "en": "[too fast]",
                    "type": 1,
                    "zh_TW": "【太快了】",
                    "ja": "【速い】",
                    "st": "$^tk^"
                }
                , {
                    "icon": "emotion\\feedback.slowly.png",
                    "url": "/static/emotion/feedback.slowly.png",
                    "zh_CN": "【太慢了】",
                    "en": "[too slow]",
                    "type": 1,
                    "zh_TW": "【太慢了】",
                    "ja": "【遅すぎる】",
                    "st": "$^tm^"
                }
                , {
                    "icon": "emotion\\feedback.think.png",
                    "url": "/static/emotion/feedback.think.png",
                    "zh_CN": "【值得思考】",
                    "en": "[be consideration]",
                    "type": 1,
                    "zh_TW": "【值得思考】",
                    "ja": "【約ワース思考】",
                    "st": "$^sk^"
                }
                , {
                    "icon": "emotion\\rose.down.png",
                    "url": "/static/emotion/rose.down.png",
                    "zh_CN": "【凋谢】",
                    "en": "[wither]",
                    "type": 3,
                    "zh_TW": "【凋謝】",
                    "ja": "【枯れて落ちる】",
                    "st": "$^dx^"
                }
                , {
                    "icon": "emotion\\rose.up.png",
                    "url": "/static/emotion/rose.up.png",
                    "zh_CN": "【鲜花】",
                    "en": "[flower]",
                    "type": 3,
                    "zh_TW": "【鮮花】",
                    "ja": "【フラワーズ】",
                    "st": "$^xh^"
                }
                , {
                    "icon": "emotion\\expression.deyi.png",
                    "url": "/static/emotion/expression.deyi.png",
                    "zh_CN": "【得意】",
                    "en": "[proud]",
                    "type": 1,
                    "zh_TW": "【得意】",
                    "ja": "【自慢】",
                    "st": "$^dy^"
                }
                , {
                    "icon": "emotion\\expression.huaixiao.png",
                    "url": "/static/emotion/expression.huaixiao.png",
                    "zh_CN": "【坏笑】",
                    "en": "[snicker]",
                    "type": 1,
                    "zh_TW": "【壞笑】",
                    "ja": "【含み笑】",
                    "st": "$^hxiao^"
                }
                , {
                    "icon": "emotion\\expression.keai.png",
                    "url": "/static/emotion/expression.keai.png",
                    "zh_CN": "【可爱】",
                    "en": "[cute]",
                    "type": 1,
                    "zh_TW": "【可愛】",
                    "ja": "【かわいい】",
                    "st": "$^ka^"
                }
                , {
                    "icon": "emotion\\expression.xigua.png",
                    "url": "/static/emotion/expression.xigua.png",
                    "zh_CN": "【西瓜】",
                    "en": "[watermelon]",
                    "type": 1,
                    "zh_TW": "【西瓜】",
                    "ja": "【スイカ】",
                    "st": "$^xg^"
                }
                , {
                    "icon": "emotion\\expression.kafei.png",
                    "url": "/static/emotion/expression.kafei.png",
                    "zh_CN": "【咖啡】",
                    "en": "[coffee]",
                    "type": 1,
                    "zh_TW": "【咖啡】",
                    "ja": "【コーヒー】",
                    "st": "$^kf^"
                }
                , {
                    "icon": "emotion\\expression.aixin.png",
                    "url": "/static/emotion/expression.aixin.png",
                    "zh_CN": "【爱心】",
                    "en": "[heart]",
                    "type": 1,
                    "zh_TW": "【愛心】",
                    "ja": "【ハート】",
                    "st": "$^ax^"
                }
                , {
                    "icon": "emotion\\expression.wshou.png",
                    "url": "/static/emotion/expression.wshou.png",
                    "zh_CN": "【握手】",
                    "en": "[shaking]",
                    "type": 1,
                    "zh_TW": "【握手】",
                    "ja": "【握手】",
                    "st": "$^ws^"
                }
                , {
                    "icon": "emotion\\expression.shengli.png",
                    "url": "/static/emotion/expression.shengli.png",
                    "zh_CN": "【胜利】",
                    "en": "[victory]",
                    "type": 1,
                    "zh_TW": "【勝利】",
                    "ja": "【勝利】",
                    "st": "$^sl^"
                }
                , {
                    "icon": "emotion\\expression.baoquan.png",
                    "url": "/static/emotion/expression.baoquan.png",
                    "zh_CN": "【抱拳】",
                    "en": "[folded hands]",
                    "type": 1,
                    "zh_TW": "【抱拳】",
                    "ja": "【お願い】",
                    "st": "$^bq^"
                }
                , {
                    "icon": "emotion\\expression.geili.png",
                    "url": "/static/emotion/expression.geili.png",
                    "zh_CN": "【给力】",
                    "en": "[awesome]",
                    "type": 1,
                    "zh_TW": "【給力】",
                    "ja": "【凄い】",
                    "st": "$^gl^"
                }
                , {
                    "icon": "emotion\\emotion.angerly.gif",
                    "url": "/static/emotion/emotion.angerly.gif",
                    "zh_CN": "【愤怒】",
                    "en": "[angry]",
                    "type": 1,
                    "zh_TW": "【憤怒】",
                    "ja": "【怒り】",
                    "st": "$^fn^"
                }
                , {
                    "icon": "emotion\\emotion.bs.gif",
                    "url": "/static/emotion/emotion.bs.gif",
                    "zh_CN": "【鄙视】",
                    "en": "[despise]",
                    "type": 1,
                    "zh_TW": "【鄙視】",
                    "ja": "【軽蔑】",
                    "st": "$^qm^"
                }
                , {
                    "icon": "emotion\\emotion.cry.gif",
                    "url": "/static/emotion/emotion.cry.gif",
                    "zh_CN": "【伤心】",
                    "en": "[sad]",
                    "type": 1,
                    "zh_TW": "【傷心】",
                    "ja": "【悲しい】",
                    "st": "$^sx^"
                }
                , {
                    "icon": "emotion\\emotion.goodbye.gif",
                    "url": "/static/emotion/emotion.goodbye.gif",
                    "zh_CN": "【再见】",
                    "en": "[bye]",
                    "type": 1,
                    "zh_TW": "【再見】",
                    "ja": "【さようなら】",
                    "st": "$^zj^"
                }
                , {
                    "icon": "emotion\\emotion.laugh.gif",
                    "url": "/static/emotion/emotion.laugh.gif",
                    "zh_CN": "【高兴】",
                    "en": "[happy]",
                    "type": 1,
                    "zh_TW": "【高興】",
                    "ja": "【ハッピー】",
                    "st": "$^gx^"
                }
                , {
                    "icon": "emotion\\emotion.lh.gif",
                    "url": "/static/emotion/emotion.lh.gif",
                    "zh_CN": "【流汗】",
                    "en": "[sweat]",
                    "type": 1,
                    "zh_TW": "【流汗】",
                    "ja": "【汗】",
                    "st": "$^lh^"
                }
                , {
                    "icon": "emotion\\emotion.nod.gif",
                    "url": "/static/emotion/emotion.nod.gif",
                    "zh_CN": "【无聊】",
                    "en": "[bored]",
                    "type": 1,
                    "zh_TW": "【無聊】",
                    "ja": "【退屈した】",
                    "st": "$^wl^"
                }
                , {
                    "icon": "emotion\\emotion.question.gif",
                    "url": "/static/emotion/emotion.question.gif",
                    "zh_CN": "【疑问】",
                    "en": "[doubt]",
                    "type": 1,
                    "zh_TW": "【疑問】",
                    "ja": "【疑い】",
                    "st": "$^yw^"
                }
                , {
                    "icon": "emotion\\emotion.smile.gif",
                    "url": "/static/emotion/emotion.smile.gif",
                    "zh_CN": "【你好】",
                    "en": "[hello]",
                    "type": 1,
                    "zh_TW": "【你好】",
                    "ja": "【こんにちは】",
                    "st": "$^nh^"
                }
                , {
                    "icon": "emotion\\feedback.against.gif",
                    "url": "/static/emotion/feedback.against.gif",
                    "zh_CN": "【反对】",
                    "en": "[oppose]",
                    "type": 1,
                    "zh_TW": "【反對】",
                    "ja": "【反対】",
                    "st": "$^fd^"
                }
                , {
                    "icon": "emotion\\emotion.bz.gif",
                    "url": "/static/emotion/emotion.bz.gif",
                    "zh_CN": "【闭嘴】",
                    "en": "[Silence]",
                    "type": 1,
                    "zh_TW": "【閉嘴】",
                    "ja": "【シャット】",
                    "st": "$^bz^"
                }
                , {
                    "icon": "emotion\\emotion.fd.gif",
                    "url": "/static/emotion/emotion.fd.gif",
                    "zh_CN": "【奋斗】",
                    "en": "[Strive]",
                    "type": 1,
                    "zh_TW": "【奮鬥】",
                    "ja": "【闘争】",
                    "st": "$^fed^"
                }
                , {
                    "icon": "emotion\\emotion.gg.gif",
                    "url": "/static/emotion/emotion.gg.gif",
                    "zh_CN": "【尴尬】",
                    "en": "[Embarrassed]",
                    "type": 1,
                    "zh_TW": "【尷尬】",
                    "ja": "【厄介な】",
                    "st": "$^gg^"
                }
                , {
                    "icon": "emotion\\emotion.gz.gif",
                    "url": "/static/emotion/emotion.gz.gif",
                    "zh_CN": "【鼓掌】",
                    "en": "[Applause]",
                    "type": 1,
                    "zh_TW": "【鼓掌】",
                    "ja": "【拍手を送る】",
                    "st": "$^ps^"
                }
                , {
                    "icon": "emotion\\emotion.hx.gif",
                    "url": "/static/emotion/emotion.hx.gif",
                    "zh_CN": "【害羞】",
                    "en": "[Shy]",
                    "type": 1,
                    "zh_TW": "【害羞】",
                    "ja": "【シャイ】",
                    "st": "$^hx^"
                }
                , {
                    "icon": "emotion\\emotion.jk.gif",
                    "url": "/static/emotion/emotion.jk.gif",
                    "zh_CN": "【惊恐】",
                    "en": "[Panic]",
                    "type": 1,
                    "zh_TW": "【驚恐】",
                    "ja": "【ショック】",
                    "st": "$^jk^"
                }
                , {
                    "icon": "emotion\\emotion.jy.gif",
                    "url": "/static/emotion/emotion.jy.gif",
                    "zh_CN": "【惊讶】",
                    "en": "[Surprised]",
                    "type": 1,
                    "zh_TW": "【驚訝】",
                    "ja": "【驚き】",
                    "st": "$^jy^"
                }
                , {
                    "icon": "emotion\\emotion.kb.gif",
                    "url": "/static/emotion/emotion.kb.gif",
                    "zh_CN": "【抠鼻】",
                    "en": "[Pick Nose]",
                    "type": 1,
                    "zh_TW": "【摳鼻】",
                    "ja": "【鼻を引いて】",
                    "st": "$^kb^"
                }
                , {
                    "icon": "emotion\\emotion.kl.gif",
                    "url": "/static/emotion/emotion.kl.gif",
                    "zh_CN": "【可怜】",
                    "en": "[Whimper]",
                    "type": 1,
                    "zh_TW": "【可憐】",
                    "ja": "【哀れな】",
                    "st": "$^kl^"
                }
                , {
                    "icon": "emotion\\emotion.ll.gif",
                    "url": "/static/emotion/emotion.ll.gif",
                    "zh_CN": "【流泪】",
                    "en": "[Sob]",
                    "type": 1,
                    "zh_TW": "【流淚】",
                    "ja": "【涙】",
                    "st": "$^ll^"
                }
                , {
                    "icon": "emotion\\emotion.qd.gif",
                    "url": "/static/emotion/emotion.qd.gif",
                    "zh_CN": "【敲打】",
                    "en": "[Hammer]",
                    "type": 1,
                    "zh_TW": "【敲打】",
                    "ja": "【ビート】",
                    "st": "$^qd^"
                }
                , {
                    "icon": "emotion\\emotion.qh.gif",
                    "url": "/static/emotion/emotion.qh.gif",
                    "zh_CN": "【强悍】",
                    "en": "[Thumbs Up]",
                    "type": 1,
                    "zh_TW": "【強悍】",
                    "ja": "【ダウティ】",
                    "st": "$^qh^"
                }
                , {
                    "icon": "emotion\\emotion.qq.gif",
                    "url": "/static/emotion/emotion.qq.gif",
                    "zh_CN": "【亲亲】",
                    "en": "[Pucker]",
                    "type": 1,
                    "zh_TW": "【親親】",
                    "ja": "【キス】",
                    "st": "$^qq^"
                }
                , {
                    "icon": "emotion\\emotion.rb.gif",
                    "url": "/static/emotion/emotion.rb.gif",
                    "zh_CN": "【弱爆】",
                    "en": "[Thumbs Down]",
                    "type": 1,
                    "zh_TW": "【弱爆】",
                    "ja": "【弱いバースト】",
                    "st": "$^rb^"
                }
                , {
                    "icon": "emotion\\emotion.se.gif",
                    "url": "/static/emotion/emotion.se.gif",
                    "zh_CN": "【色】",
                    "en": "[Drooling]",
                    "type": 1,
                    "zh_TW": "【色】",
                    "ja": "【色】",
                    "st": "$^se^"
                }
                , {
                    "icon": "emotion\\emotion.tx.gif",
                    "url": "/static/emotion/emotion.tx.gif",
                    "zh_CN": "【偷笑】",
                    "en": "[Chuckle]",
                    "type": 1,
                    "zh_TW": "【偷笑】",
                    "ja": "【クスクス笑い】",
                    "st": "$^tx^"
                }
                , {
                    "icon": "emotion\\emotion.xu.gif",
                    "url": "/static/emotion/emotion.xu.gif",
                    "zh_CN": "【嘘】",
                    "en": "[Shh]",
                    "type": 1,
                    "zh_TW": "【噓】",
                    "ja": "【ヒス】",
                    "st": "$^xu^"
                }
                , {
                    "icon": "emotion\\emotion.yun.gif",
                    "url": "/static/emotion/emotion.yun.gif",
                    "zh_CN": "【晕】",
                    "en": "[Hypnotized]",
                    "type": 1,
                    "zh_TW": "【暈】",
                    "ja": "【目まいがする】",
                    "st": "$^yun^"
                }
            ]
        },
        emotionImageUrl: function (richText, prefix) {
            var that = this;
            if (!GS.COMMON.utils.tools.isEmpty(prefix)) {
                this.configs.prefix = prefix;
            }
            if (this.configs.prefix.lastIndexOf("/") == (this.configs.prefix.length - 1)) {
                this.configs.prefix == this.configs.prefix.substring(0, this.configs.prefix.length - 1)
            }
            var richText = richText.replace(/\<img.+?src=\s*?\S(.+?(gif|png))\s*("|').*?\>/gi, function (replaceStr, imgurl) {
                for (var i = 0; i < that.configs.list.length; i++) {
                    if (compareEmotionURL(that.configs.prefix + that.configs.list[i].url, imgurl)) {
                        replaceStr = replaceStr.replace(imgurl, that.configs.list[i].icon);
                        break;
                    } else if (i == that.configs.list.length - 1) {
                        replaceStr = "";
                    }
                }
                return replaceStr;
            });

            function compareEmotionURL(url1, url2) {
                var urlobj1 = transcodeUrl(url1);
                var urlobj2 = transcodeUrl(url2);
                if (urlobj1.host == urlobj2.host && urlobj1.file == urlobj2.file && (urlobj2.protocol == "http:" || urlobj2.protocol == "https:")) {
                    return true;
                } else {
                    return false;
                }
            }

            function transcodeUrl(url) {
                var urlobj = {protocol: "http:", port: "80", host: "", file: ""};
                var protocolindex = url.indexOf("://") + 1;
                if (protocolindex > 0) {
                    urlobj.protocol = url.substring(0, protocolindex);
                    var prexindex = url.indexOf("/", protocolindex + 2);
                    if (prexindex > 0) {
                        var prex = url.substring(protocolindex + 2, prexindex);
                        var portindex = prex.indexOf(":");
                        if (portindex > 0) {
                            urlobj.host = prex.substring(0, portindex);
                            urlobj.port = prex.substring(portindex + 1);
                        } else {
                            urlobj.host = prex;
                        }
                        urlobj.file = url.substring(prexindex);
                    }
                }
                return urlobj;
            }

            return richText;
        },
        emotionText: function (richText, prefix) {
            var that = this;
            if (!GS.COMMON.utils.tools.isEmpty(prefix)) {
                this.configs.prefix = prefix;
            }
            if (this.configs.prefix.lastIndexOf("/") == (this.configs.prefix.length - 1)) {
                this.configs.prefix == this.configs.prefix.substring(0, this.configs.prefix.length - 1)
            }
            var richText = richText.replace(/\<img.+?src=\s*?\S(.+?(gif|png))\s*("|').*?\>/gi, function (replaceStr, imgurl) {
                for (var i = 0; i < that.configs.list.length; i++) {
                    if (that.configs.list[i].icon == imgurl) {
                        replaceStr = that.configs.list[i][that.language];
                        break;
                    }
                }
                return replaceStr;
            });
            return richText;
        },
        emotionImage: function (richText, prefix) {
            var that = this;
            if (!GS.COMMON.utils.tools.isEmpty(prefix)) {
                this.configs.prefix = prefix;
            }
            if (this.configs.prefix.lastIndexOf("/") == (this.configs.prefix.length - 1)) {
                this.configs.prefix == this.configs.prefix.substring(0, this.configs.prefix.length - 1);
            }

            var richText = richText.replace(/\<img.+?src=\s*?\S(.+?(gif|png))\s*("|').*?\>/gi, function (replaceStr, imgurl) {
                for (var i = 0; i < that.configs.list.length; i++) {
                    if (that.configs.list[i].icon == imgurl) {
                        replaceStr = replaceStr.replace(that.configs.list[i].icon, that.configs.prefix + that.configs.list[i].url);
                        break;
                    }
                }
                return replaceStr;
            });
            return richText;
        },
        emotionEscapeImage: function (richText) {
            for (var i = 0; i < this.configs.list.length; i++) {
                var st = this.configs.list[i].st;
                var img = '<img src="' + this.configs.list[i].icon + '"/>';
                while (richText.indexOf(st) >= 0) {
                    richText = richText.replace(st, img);
                }
            }
            return richText;
        }
    }

    window.GS.COMMON.emotions = emotions;
})(window, undefined);

//日志
(function (window) {
    var websocket = null;
    var isOpen = false;
    var isClosed = false;
    var msgArray = new Array();
    var logCache = new Array();
    var logObject = function (ip, port, sessionId, fun) {
        if (ip.indexOf("ws") != 0) {
            ip = "ws://" + ip
        }
        var url = ip + ":" + port;
        try {
            websocket = new WebSocket(url);
            websocket.onopen = function () {
                isOpen = true;
                isClosed = false;
                websocket.send("startSend:" + sessionId);
                for (var i = 0; i < msgArray.length; i++) {
                    websocket.send("[" + new Date().toTimeString() + "]" + msgArray[i]);
                }
                msgArray = new Array()
            };
            websocket.onclose = function () {
                isOpen = false;
                isClosed = true;
            };
            websocket.onerror = function (data) {

            }
        } catch (e) {
            console.log(e);
        }
    };
    logObject.prototype.send = function (msg) {
        if (websocket != null && isOpen) {
            websocket.send(msg);
        } else {
            if (!isClosed) {
                msgArray.push(msg);
            }
            return false;
        }
    };
    logObject.prototype.isClosed = function () {
        return isClosed;
    };
    logObject.prototype.isOpen = function () {
        return isOpen;
    };
    window.logUtils = logObject;

    var LINSENER_START_DEBUG = "linsener_start_debug_anroid_gensee_";
    var staticLog = console.log;
    var staticDebug = console.debug;
    var staticInfo = console.info;
    var staticWarn = console.warn;
    var staticError = console.error;
    var LINSENER_OPEN = "open";
    var LINSENER_CLOSED = "closed";
    var LOG_STAET_DEBUG_KEY = "log_start_debug_key";
    var LOG_STAET_KEY_VAR = 0;
    var LINSENER_ALREADY_STATUS = false;
    var isSend = false;
    var logObject = null;
    var isWebcoscket = false;
    var isOpenLog = false;
    var debugLog = function (msg) {
        var dateTime = new Date();
        if (isWebcoscket) {
            msg = "type=websocket:" + "[" + dateTime.toTimeString() + "]" + msg;
        } else {
            msg = "[" + new Date().toTimeString() + "]" + msg;
        }
        if (logObject != null) {
            if (logObject.isOpen() || !logObject.isClosed()) {
                logObject.send(msg);
            }
        }
        if (LINSENER_ALREADY_STATUS) {
            if (logObject == null || !logObject.isClosed()) {
                var storage = window.localStorage;
                storage.setItem(LOG_STAET_DEBUG_KEY + LOG_STAET_KEY_VAR, msg);
                LOG_STAET_KEY_VAR++;
            }
        }
    };
    var logConsole = function (msg) {
        if (isSend) {
            debugLog(msg);
        }
    };
    var debugConsole = function (msg) {
        if (isSend) {
            debugLog(msg);
        }
    };
    var infoConsole = function (msg) {
        if (isSend) {
            debugLog(msg);
        }
    };
    var warnConsole = function (msg) {
        if (isSend) {
            debugLog(msg);
        }
    };
    var errorLog = function (msg) {
        if (isSend) {
            debugLog(msg);
        }
    };

    function linserLog() {
        if (isOpenLog) {
            return;
        }
        isOpenLog = true;
        if (location.href.indexOf("debug=true") > 0) return;
        if (searchHerf("logip") != null && searchHerf("logip").length > 0) {
            console.log = logConsole;
            console.debug = debugConsole;
            console.info = infoConsole;
            console.warn = warnConsole;
            console.error = errorLog;
            if (!openWebsocket(function () {
                openDebugLog();
            })) {
                openDebugLog();
            }
        } else {
            openDebugLog();
        }
    }

    function openDebugLog() {
        initLog();
        if (window.addEventListener) {
            window.addEventListener("storage", function () {
                initLog();
            }, false);
        } else if (window.attachEvent) {
            window.attachEvent("onstorage", function () {
                initLog();
            });
        }
    }

    function initLog() {
        var storage = window.localStorage;
        var debugValue = storage.getItem(LINSENER_START_DEBUG);
        if (debugValue == null) {
            return;
        }
        if (debugValue == LINSENER_OPEN && !LINSENER_ALREADY_STATUS) {
            console.log = logConsole;
            console.debug = debugConsole;
            console.info = infoConsole;
            console.warn = warnConsole;
            console.error = errorLog;
            LINSENER_ALREADY_STATUS = true;
            isSend = true;
            LOG_STAET_KEY_VAR = 0;

        } else if (debugValue == LINSENER_CLOSED && LINSENER_ALREADY_STATUS) {
            console.log = logConsole;
            console.debug = debugConsole;
            console.info = infoConsole;
            console.warn = warnConsole;
            console.error = errorLog;
            LINSENER_ALREADY_STATUS = false;
            isSend = false;
        }
    }

    function openWebsocket(closedFun) {
        var ip = searchHerf("logip");
        if (ip != null) {
            var port = searchHerf("logport");
            var sessionId = searchHerf("logid");
            if (port == null) {
                port = 888;
            }
            if (sessionId == null) {
                sessionId = 1;
            }
            try {
                isSend = true;
                logObject = new window.logUtils(ip, port, sessionId, closedFun);
                return true;
            } catch (e) {
                isSend = false;
                console.log(e);
            }
        }
        return false;
    }

    function searchHerf(name) {
        var searchString = document.location.search;
        if (searchString.length > 0) {
            searchString = searchString.substring(1);
        }
        var searchArray = searchString.split("&");
        for (var i = 0; i < searchArray.length; i++) {
            var keyValue = searchArray[i].split("=");
            if (keyValue.length == 2) {
                if (keyValue[0] == name) {
                    return keyValue[1];
                }
            }
        }
        return null;
    }

    //linserLog();

    var logger = (function (name) {
        var _logger = this;
        this.name = name;
        this.logs = [];
        var levels = {"DEBUG": 0, "INFO": 1, "WARN": 2, "ERROR": 3, "FATAL": 4};
        this.getQueryString = function (paramName) {
            paramValue = "", isFound = !1;
            if (location.search.indexOf("?") == 0 && location.search.indexOf("=") > 1) {
                arrSource = unescape(location.search).substring(1, location.search.length).split("&"), i = 0;
                while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
            }
            return paramValue == "" && (paramValue = null), paramValue;
        };
        var urllevel = this.getQueryString('loglevel');
        this.level = urllevel != null ? urllevel : "INFO";

        this.out = function (log) {
            if (urllevel != null) {
                console.log(log);
            } else {
                if (searchHerf("logip") != null && searchHerf("logip").length > 0) {
                    linserLog();
                    console.log(JSON.stringify(log));
                } else {
                    _logger.logs.push(log);
                }
            }
        };
        this.log = function (msg, type, data) {
            type = type ? type : "info";
            //check level
            var grade = levels[type.toUpperCase()];
            var outGrade = levels[_logger.level.toUpperCase()];
            if (typeof grade === "number" && typeof outGrade === "number") {
                if (grade < outGrade) {
                    return;
                }
            }
            if (typeof msg !== "string") {
                this.log("log(msg, type), type of msg must be 'string'", "ERROR", msg);
                return;
            }
            var logObj = {time: new Date(), msg: msg, type: type, data: data};
            if (grade >= outGrade) {
                var logStr = "[ " + logObj.time.toString() + "] " + logObj.msg + " " + JSON.stringify(logObj.data);
                logCache.push(logStr);

                if (logCache.length > 2000) {
                    logCache.shift();
                }
            }
            _logger.out(logObj);
        };
        this.moveLogs = function (n) {
            return _logger.logs.splice(0, n || _logger.logs.length);
        };
    });
    window.GS.COMMON.logger = logger;
    window.GS.COMMON.logCache = logCache;
})(window, undefined);

//模版
(function (window) {
    var tmpl = function tmpl(str, data, wg, hlp) {
        var fn = new Function("data", "GsX", "wg", "hlp",
            "var p=[],print=function(){p.push.apply(p,arguments);};" +
            "p.push('" +
            str.replace(/[\r\t\n]/g, " ")
                .split("<%").join("\t")
                .replace(/((^|%>)[^\t]*)'/g, "$1\r")
                .replace(/\t=(.*?)%>/g, "',$1,'")
                .split("\t").join("');")
                .split("%>").join("p.push('")
                .split("\r").join("\\'") +
            "');return p.join('');");
        return data ? fn(data, GS._GsX_, wg, hlp) : fn;
    };

    window.GS.COMMON.tmpls = tmpl;
}(window, undefined));

Process
finished
with exit code
0