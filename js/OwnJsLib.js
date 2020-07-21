var qs="";
var k;
function $$$$$(elem)
{
var element=elem;//private property
this.val=function()//public method
{
 if(element.value)return element.value;
 throw "Component doesn't have value property";
 return element;
}
this.html=function(content)
{
 element.innerHTML=content;
}
}
function $$$(elemId)
{
var elem=document.getElementById(elemId);
if(!elem) throw "Invalid Element Id:"+elemId;
return new $$$$$(elem);
}
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
$$$.postJSON=function(jsonObject)
{
if(!jsonObject.url) throw "url prperty missing in json/url is boolean";
if(typeof jsonObject.url!="string") throw "url prperty should be of string type";
if(jsonObject.data)
{
//qs=getQueryString(jsonObject.data);
//alert(qs);
}
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
  console.log(responseJSON);
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
xhr.open("POST",jsonObject.url,false);
xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xhr.send(JSON.stringify(jsonObject.data));
}