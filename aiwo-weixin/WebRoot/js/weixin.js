function getAppId(){
	return "wxa6309997e9c15c83" ;
}

function getTimeStamp() {
    var timestamp = new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    oldTimeStamp = timestampstring;
    return timestampstring;
}


function getNonceStr() {
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    var noceStr = "";
    for (var i = 0; i < 32; i++) {
        noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    oldNonceStr = noceStr;
    return noceStr;
}


function getCode(){
	
	return '${code}' ;
}

/**
 * 
 *  获取accesstoken
 * @returns
 */
function getaccesstoken(){
	//alert("${accesstoken}".toString());
	return $("#accesstoken").val().toString() ;
	
}

function perapara(objvalues, isencode) {
    var parastring = "";
    for (var key in objvalues) {
        isencode = isencode || false;
        if (isencode) {
            parastring += (key + "=" + encodeURIComponent(objvalues[key]) + "&");
        }
        else {
            parastring += (key + "=" + objvalues[key] + "&");
        }
    }
    parastring = parastring.substr(0, parastring.length - 1);
    alert(parastring);
    return parastring;
}

function getSign(beforesingstring) {
    sign = CryptoJS.SHA1(beforesingstring).toString();
    return sign;
}

var signparasobj = {
        "accesstoken": "",
        "appid": getAppId(),
        "noncestr": "",
        "timestamp": "",
        "url": ""
    };