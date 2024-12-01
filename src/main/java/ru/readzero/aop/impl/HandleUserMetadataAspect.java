package ru.readzero.aop.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.readzero.aop.HandleUserMetadata;
import ru.readzero.entity.session.Session;
import ru.readzero.enums.session.DeviceType;
import ru.readzero.enums.session.SessionActionType;
import ru.readzero.enums.session.SystemType;
import ru.readzero.repository.SessionRepository;
import ru.readzero.util.UserUtil;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HandleUserMetadataAspect {

    HttpServletRequest request;

    SessionRepository sessionRepository;

    @Around("@annotation(handleUserMetadata)")
    public Object handleMetadata(ProceedingJoinPoint joinPoint, HandleUserMetadata handleUserMetadata) throws Throwable {
        String ip = request.getRemoteAddr();
        String locale = request.getLocale().toString();
        String userAgent = request.getHeader("User-Agent");
        SessionActionType actionType = handleUserMetadata.actionType();


        Session session = new Session();
        session.setIp(ip);
        if (actionType != SessionActionType.REGISTER) {
            session.setUser(UserUtil.getCurrentUser());
        }
        session.setActionType(actionType);
        session.setSystem(this.getSystemType(userAgent));
        session.setDeviceType(this.getDeviceTypeFromUserAgent(userAgent));
        session.setLocale(locale);
        sessionRepository.save(session);

        return joinPoint.proceed();
    }

    private SystemType getSystemType(String systemType) {
        if (systemType == null)
            return SystemType.OTHER;

        systemType = systemType.toLowerCase();

        if (systemType.contains("windows")) {
            return SystemType.WINDOWS;
        } else if (systemType.contains("linux")) {
            return SystemType.LINUX;
        } else if (systemType.contains("mac")) {
            return SystemType.MAC_OS;
        } else if (systemType.contains("android")) {
            return SystemType.ANDROID;
        } else if (systemType.contains("iphone") || systemType.contains("ipad")) {
            return SystemType.IOS;
        } else {
            return SystemType.OTHER;
        }
    }

    private DeviceType getDeviceTypeFromUserAgent(String userAgent) {
        if (userAgent == null)
            return DeviceType.OTHER;

        userAgent = userAgent.toLowerCase();

        if (userAgent.contains("windows") || userAgent.contains("mac") || userAgent.contains("linux")) {
            return DeviceType.DESKTOP;
        } else if (userAgent.contains("mobile")) {
            return DeviceType.MOBILE;
        } else if (userAgent.contains("tablet")) {
            return DeviceType.TABLET;
        } else if (userAgent.contains("googlebot") || userAgent.contains("bot")) {
            return DeviceType.BOT;
        } else {
            return DeviceType.OTHER;
        }
    }

}
