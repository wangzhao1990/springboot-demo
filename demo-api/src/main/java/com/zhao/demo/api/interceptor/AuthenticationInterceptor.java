package com.zhao.demo.api.interceptor;

import com.zhao.demo.common.annotation.Logical;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author wangzhao
 * 2019-10-21
 * <p>
 * 权限拦截器
 */
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

//    @Resource
//    private HwMerchantLoginMapper hwMerchantLoginMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {

        // 如果拦截的不是方法则直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        httpServletResponse.setHeader("token",token);
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();

//        HwMerchantLogin hwMerchantLogin = null;
//        // 代表，需要登录认证后才能进行的操作。即，游客无法进行该操作。
//        if (method.isAnnotationPresent(RequiresAuthentication.class) || method.getDeclaringClass().isAnnotationPresent(RequiresAuthentication.class)) {
//            hwMerchantLogin = checkAuthentication(token);
//            httpServletRequest.setAttribute("loginId",hwMerchantLogin.getId());// 登录id
//            httpServletRequest.setAttribute("account",hwMerchantLogin.getAccount());// 登录账号
//            httpServletRequest.setAttribute("merchantId",hwMerchantLogin.getHwMerchantInfoId());// 商户id
//        }


//        // 代表，需要某些身份/角色才能进行的操作。不是该角色的用户无法操作。
//        else if (method.isAnnotationPresent(RequiresRoles.class)) {
//            RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
//            String[] requiresRoleNames = requiresRoles.value();
//            Logical logical = requiresRoles.logical();
//
//            if (!hasRoles(token, Sets.newHashSet(requiresRoleNames), logical)) {
//                log.info("权限验证失败：不具备指定身份 {}", Arrays.toString(requiresRoleNames));
////                throw new AuthorizationException();
//                throw new CommonException(401,"不具备指定身份");
//            }
//        }
//
//        //  代表，需要某种权限才能进行的操作。没有拥有该角色的用户无法操作。
//        else if (method.isAnnotationPresent(RequiresPermissions.class)) {
//            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
//            String[] requiresPermissionNames = requiresPermissions.value();
//            Logical logical = requiresPermissions.logical();
//
//            if(!hasPermissions(token, Sets.newHashSet(requiresPermissionNames), logical)) {
//                log.info("权限验证失败：不具备指定权限 {}", Arrays.toString(requiresPermissionNames));
////                throw new AuthorizationException();
//                throw new CommonException(401,"不具备指定权限");
//            }
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


//    /**
//     * 有 token，且 token 合法，即代表曾经登录过。
//     */
//    private HwMerchantLogin checkAuthentication(String token) throws CommonException {
//        if (Strings.isNullOrEmpty(token)) {
//            throw new CommonException(400,"无 token，请重新登录");
//        }
//
//        // 获取 token 中的 user id
//        String userId = JWTUtil.getUserID(token);
//        if (userId == null) {
//            throw new CommonException(400,"未登录");
//        }
//
//        HwMerchantLogin hwMerchantLogin = hwMerchantLoginMapper.selectByPrimaryKey(userId);
//        // 校验用户是否存在
//        if (hwMerchantLogin == null) {
//            throw new CommonException(400,"用户不存在，请重新登录");
//        }
//
//        if (!JWTUtil.verify(token)) {
//            throw new CommonException(400,"token无效，请重新登录");
//        }
//
//        // 密码校验是否变更
//        String userPwd = JWTUtil.getUserPwd(token);
//        String spwd = DigestUtils.md5Hex(userPwd.toUpperCase() + hwMerchantLogin.getSalt()).toUpperCase();
//        if (!spwd.equals(hwMerchantLogin.getPassword())) {
//            throw new CommonException(400,"用户密码变更，请重新登录");
//        }
//        return hwMerchantLogin;
//
//    }

    private boolean hasRoles(String token, Set<String> requiredRoleNames, Logical logical) {
//        User user = checkAuthentication(token);
//        if (user == null)
//            return false;
//
//        Set<String> roleNames = user.getRoleNames();
//
//        if (logical == Logical.AND)
//            return roleNames.containsAll(requiredRoleNames);
//        else
//            return !Sets.intersection(roleNames, requiredRoleNames).isEmpty();
        return true;
    }


    private boolean hasPermissions(String token, Set<String> requiredPermissionNames, Logical logical) {
//        User user = checkAuthentication(token);
//        if (user == null)
//            return false;
//
//        Set<String> permissionNames = user.getPermissionNames();
//        if (logical == Logical.AND)
//            return permissionNames.containsAll(requiredPermissionNames);
//        else
//            return !Sets.intersection(permissionNames, requiredPermissionNames).isEmpty();
        return true;
    }
}
