package com.umm.wfm.service.message;

import com.umm.wfm.intf.dto.member.LoginReqDto;
import com.umm.wfm.intf.entity.WfmLoginLogEntity;
import com.umm.wfm.intf.tsp.dto.mmc.MessageBody;
import com.umm.wfm.security.log.LogUtil;
import com.umm.wfm.utils.DateUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wangshiqi on 2017/7/18.
 */
@Service
public class MessageSendFacadeService {
    private static final Logger LOGGER = LogUtil.getLogger(MessageSendFacadeService.class);

    @Resource
    private TspMmcInterface tspMmcInterface;

    public void sendDeviceAppMessage(LoginReqDto currentLogin, WfmLoginLogEntity lastLogin) {
        LOGGER.info("sendDeviceAppMessage start, memberId:{}", lastLogin.getMemberId());
        MessageBody.MsgContent content = new MessageBody.MsgContent();
        MessageBody.MsgAction action = new MessageBody.MsgAction();
        MessageBody.MsgPayload payload = new MessageBody.MsgPayload();
        MessageBody messageBody = new MessageBody();

        content.setDisplayType(WfmCommonConstant.ChangeDeviceMsg.DISPLAY_TYPE);
        content.setTitle(WfmCommonConstant.ChangeDeviceMsg.TITLE);
        content.setTicker(WfmCommonConstant.ChangeDeviceMsg.TICKER);
        String text = WfmCommonConstant.ChangeDeviceMsg.TEXT
                .replace("{loginTime}", DateUtils.formatDateTime(new Date()))
                .replace("{deviceName}", currentLogin.getDeviceName());
        content.setText(text);

        action.setAfterOpen(WfmCommonConstant.ChangeDeviceMsg.AFTER_OPEN); //打开app

        payload.setContent(content);
        payload.setAction(action);

        messageBody.setPayload(payload);
        messageBody.setAppType(1);  // 1：小黑鱼 2：急用钱
        messageBody.setDeviceToken(lastLogin.getDeviceToken());  // 友盟设备号
        Integer deviceType = "ios".equalsIgnoreCase(lastLogin.getSystemNo()) ? 1 : 0;
        messageBody.setDeviceType(deviceType);  // 0-安卓 1-IOS
        messageBody.setDescription(WfmCommonConstant.ChangeDeviceMsg.DESCRIPTION);
        messageBody.setMsgType(WfmCommonConstant.ChangeDeviceMsg.MSG_TYPE);  // 约定值,枚举wiki.umm.org/pages/viewpage.action?pageId=69546063
        messageBody.setSystemId(WfmCommonConstant.SYSTEM_CODE);

        tspMmcInterface.sendDeviceAppMessage(messageBody);
        LOGGER.info("sendDeviceAppMessage end, memberId:{}", lastLogin.getMemberId());
    }

}
