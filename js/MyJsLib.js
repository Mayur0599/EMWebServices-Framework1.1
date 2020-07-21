var qs="";
var k;

//function to convert json to query string
function getQueryString(json)
{
for(i in json)
{
qs=qs+encodeURI(i)+'='+encodeURI(json[i])+'&';
}
l=qs.length;
qs=qs.substring(0,l-1);
return qs;
}

//function to return value,setHtml,return html
function $$$$$(elem)
{
var element=elem;//private property
this.val=function()//public method
{
 if(element.value)return element.value;
 throw "Component doesn't have value property";
 return element;
}
this.html=function()//public method
{
 if(element.innerHTML)return element.innerHTML;
 throw "Component doesn't have innerHTML property";
 return element;
}
this.style=function()//public method
{
 if(element.style)return element.style;
 throw "Component doesn't have style property";
 return element;
}
this.html=function(content)
{
 element.innerHTML=content;
}
}

//function to check given id is of element or not
function $$$(elemId)
{
var elem=document.getElementById(elemId);
if(!elem) throw "Invalid Element Id:"+elemId;
return new $$$$$(elem);
}

//Post JSON Method just like jquery
$$$.postJSON=function(jsonObject)
{
if(!jsonObject.url) throw "url prperty missing in json/url is boolean";
if(typeof jsonObject.url!="string") throw "url prperty should be of string type";

if(jsonObject.success && typeof jsonObject.success!="function") throw "success property should be a function";
if(jsonObject.exception && typeof jsonObject.exception!="function") throw "exception property should be a function";
if(jsonObject.failed && typeof jsonObject.failed!="function") throw "failed property should be a function";
var xhr=new XMLHttpRequest();
xhr.onreadystatechange=function()
{
if(this.readyState==4)
 {
  if(this.status==200)
 {
  var responseString=xhr.responseText;
  console.log(responseString);
  var responseJSON=JSON.parse(responseString);
  //console.log(responseJSON);
  if(responseJSON.success)
   {
   if(jsonObject.success)
    {
    jsonObject.success(responseJSON); 
    qs="";
    }   
   }
  else
   {
   if(jsonObject.exception)
     jsonObject.exception(responseJSON.exception); 
   }
 }
 else
 {
   if(jsonObject.failed)
     jsonObject.failed();  
 }
}
}
xhr.open("POST",jsonObject.url,true);
xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
if(jsonObject.data)xhr.send(JSON.stringify(jsonObject.data));
else
xhr.send();
}

//GetJSON
$$$.getJSON=function(jsonObject)
{
if(!jsonObject.url) throw "url property missing in json/url is boolean";
if(typeof jsonObject.url!="string") throw "url property should be of string type";
if(jsonObject.success && typeof jsonObject.success!="function") throw "success property should represent a function";
if(jsonObject.exception && typeof jsonObject.exception!="function") throw "exception property should represent a function";
if(jsonObject.failed && typeof jsonObject.failed!="function") throw "failed property should represent a function";
if(jsonObject.data)
{
qs=getQueryString(jsonObject.data);
}

var xmlHttpRequest=new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
var responseString=xmlHttpRequest.responseText;
var responseJSON=JSON.parse(responseString);
if(responseJSON.success)
{
if(jsonObject.success) success(responseJSON.response);
qs="";
}
else
{
if(jsonObject.exception) jsonObject.exception(responseJSON.exception);
}
}
else
{
alert("Some server issue");
}
}
}
xmlHttpRequest.open("GET",jsonObject.url,true);
xmlHttpRequest.send();
}


