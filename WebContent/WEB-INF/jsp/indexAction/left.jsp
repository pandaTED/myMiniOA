<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>导航菜单</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
    <script type="text/javascript">
        function menuClick( menuDiv ){
            $(".MenuLevel2").not( $(menuDiv).next() ).hide();
            $(menuDiv).next().toggle();
        }
    </script>
</head>
<body style="margin: 0">

<div id="Menu">

    <ul id="MenuUl">

        <%-- 显示一级菜单 --%>
        <s:iterator value="#application.topMenuList">
            <s:if test="#session.user.hasPrivilegeByName(name)">
                <li class="level1">
                    <div onClick="menuClick(this);" class="level1Style">
                        <img src="${pageContext.request.contextPath}/style/images/MenuIcon/${icon}" class="Icon" />
                            ${name}
                    </div>

                    <ul style="display: none;" class="MenuLevel2">
                            <%-- 显示二级菜单 --%>
                        <s:iterator value="children">
                            <s:if test="#session.user.hasPrivilegeByName(name)">
                                <li class="level2">
                                    <div class="level2Style">
                                        <img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" />
                                        <a target="right" href="${pageContext.request.contextPath}/${url}.do">${name}</a>
                                    </div>
                                </li>
                            </s:if>
                        </s:iterator>
                    </ul>
                </li>
            </s:if>
        </s:iterator>

    </ul>
</div>
</body>
</html>
