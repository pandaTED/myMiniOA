<%--
  Created by IntelliJ IDEA.
  User: panda
  Date: 2015/12/19 0019
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Itcast OA</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
    <link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
    <script type="text/javascript">
        $(function(){
            document.forms[0].loginName.focus();
        });

        // 如果登录页面显示在了框架里（frame），则在顶层窗口显示登录页面（不要嵌套显示）
        if(window.parent != window){
            window.parent.location.href = window.location.href
        }

    </script>
</head>

<body >

<!-- 显示表单 -->
<s:form action="login_login" focusElement="loginNameInput">
    <div id="CenterAreaBg">
        <div id="CenterArea">
            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>
            <div id="LoginInfo">
                <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                    <tr>
                        <td colspan="3"><!-- 显示错误 -->
                            <font color="red"><s:fielderror/></font>
                        </td>
                    </tr>
                    <tr>
                        <td width=45 class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
                        <td>
                            <s:textfield name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput" />
                        </td>
                        <td rowspan="2" style="padding-left:10px;">
                            <input type="image" tabindex="3" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
                        </td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td><s:password name="password" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required" /></td>
                    </tr>
                </table>
            </div>
            <div id="CopyRight"><a href="javascript:void(0)">&copy; 2010 版权所有 itcast</a></div>
        </div>
    </div>
</s:form>
</body>
</html>

