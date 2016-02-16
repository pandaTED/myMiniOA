package org.apache.struts2.views.jsp.ui;

import cn.panda.oa.domain.User;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Anchor;
import org.apache.struts2.components.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @see Anchor
 */
public class AnchorTag extends AbstractClosingTag {

    private static final long serialVersionUID = -1034616578492431113L;

    protected String href;
    protected String includeParams;
    protected String scheme;
    protected String action;
    protected String namespace;
    protected String method;
    protected String encode;
    protected String includeContext;
    protected String escapeAmp;
    protected String portletMode;
    protected String windowState;
    protected String portletUrlType;
    protected String anchor;
    protected String forceAddSchemeHostAndPort;

    @Override
    /**
     * 重写doEndTag方法，当用户没有权限时，不显示功能的链接
     */
    public int doEndTag() throws JspException {
        // 获取当前登录用户
        User user = (User) pageContext.getSession().getAttribute("user");
        // 获取当前访问的URL（actionName），就是本类的action属性值
        // >> 去掉参数
        String url = action;
        int pos = url.indexOf('?');
        if (pos > -1) {
            url = url.substring(0, pos); // 去掉参数
        }
        // >> 去掉UI后缀（例：userAction_addUI就是userAction_add权限）
        if (url.endsWith("UI")) {
            url = url.substring(0, url.length() - 2);
        }

        if (user.hasPrivilegeByUrl(url)) {
            return super.doEndTag(); // 正常显示超链接标签，并继续执行后面的内容
        } else {
            return EVAL_PAGE; // 不显示当前的超链接标签，只继续执行后面的内容
        }
    }

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Anchor(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Anchor tag = (Anchor) component;
        tag.setHref(href);
        tag.setIncludeParams(includeParams);
        tag.setScheme(scheme);
        tag.setValue(value);
        tag.setMethod(method);
        tag.setNamespace(namespace);
        tag.setAction(action);
        tag.setPortletMode(portletMode);
        tag.setPortletUrlType(portletUrlType);
        tag.setWindowState(windowState);
        tag.setAnchor(anchor);

        if (encode != null) {
            tag.setEncode(Boolean.valueOf(encode).booleanValue());
        }
        if (includeContext != null) {
            tag.setIncludeContext(Boolean.valueOf(includeContext).booleanValue());
        }
        if (escapeAmp != null) {
            tag.setEscapeAmp(Boolean.valueOf(escapeAmp).booleanValue());
        }
        if (forceAddSchemeHostAndPort != null) {
            tag.setForceAddSchemeHostAndPort(Boolean.valueOf(forceAddSchemeHostAndPort).booleanValue());
        }
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public void setIncludeContext(String includeContext) {
        this.includeContext = includeContext;
    }

    public void setEscapeAmp(String escapeAmp) {
        this.escapeAmp = escapeAmp;
    }

    public void setIncludeParams(String name) {
        includeParams = name;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPortletMode(String portletMode) {
        this.portletMode = portletMode;
    }

    public void setPortletUrlType(String portletUrlType) {
        this.portletUrlType = portletUrlType;
    }

    public void setWindowState(String windowState) {
        this.windowState = windowState;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public void setForceAddSchemeHostAndPort(String forceAddSchemeHostAndPort) {
        this.forceAddSchemeHostAndPort = forceAddSchemeHostAndPort;
    }
}
