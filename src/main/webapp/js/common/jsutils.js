/*
 * 
 *js 工具函数
 *
 *包括 加减乘除 运算
 *
 *
 */

	/**
	*格式化金额  保留两位小数
	*/
	function formatMoney2(money){
		var moneyTemp=money.toString().split(".");
		if(moneyTemp.length==1){
			money=money+".00";
		}else if(moneyTemp.length==2&&moneyTemp[1].length==1){
			money=money+"0";
		}else if(moneyTemp.length==2&&moneyTemp[1].length > 2){
			money=moneyTemp[0]+"."+moneyTemp[1].substr(0,2);
		}
		return money;
	}
	
	function roundMoney2(money){
		return Math.round(money * Math.pow(10, 2)) / Math.pow(10, 2)
	}
	
	//乘法函数，用来得到精确的乘法结果
	function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
	}
	
	//加法函数，用来得到精确的加法结果
	function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2))
	return (arg1*m+arg2*m)/m
	}
	
	//减法函数，用来得到精确的减法结果
	function accSubtr(arg1,arg2){
	var r1,r2,m,n;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2));
	n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m).toFixed(n);
	}
	
	
	/**
	 * 获取当前工程的虚拟目录
	 * @return
	 */	
	function getRootPath(){
			var strFullPath=window.document.location.href;
			var strPath=window.document.location.pathname;
			var pos=strFullPath.indexOf(strPath);
			var prePath=strFullPath.substring(0,pos);
			var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
			return(prePath);
			}

	var _base = getRootPath();
