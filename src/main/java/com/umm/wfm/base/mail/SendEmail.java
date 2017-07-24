package com.umm.wfm.base.mail;

import com.umm.wfm.intf.tsp.dto.pla.SendEmailInternalRequest;
import com.umm.wfm.security.log.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SendEmail {

    private static final Logger LOGGER = LogUtil.getLogger(SendEmail.class);

    @Value("${mail.template.systemCode}")
    private String systemCode;

    @Value("${mail.template.businessId}")
    private String businessId;

    @Value("${mail.template.senderName}")
    private String senderName;

    @Resource
    private TspPlaInterface tspPlaInterface;

    /**
     * 采用公司统一的邮件发送模板
     * @param subject 标题
     * @param msg 内容
     * @param sendTo 接收人
     * @param sendCc cc
     */
    public void sendEmail(String subject, String msg, String sendTo, String sendCc) {
        try {
            if (StringUtils.isNotEmpty(msg) && StringUtils.isNotEmpty(sendTo)) {
                SendEmailInternalRequest request = new SendEmailInternalRequest();
                request.setSystemCode(systemCode);
                request.setBusinessId(businessId);
                request.setSenderName(senderName);
                List<String> recipientEmails = new ArrayList<String>();
                String[] emails = sendTo.split(";");
                for (String emailAddress : emails) {
                    recipientEmails.add(emailAddress);
                }
                List<String> ccEmails=new ArrayList<String>();
                if (StringUtils.isNotEmpty(sendCc)) {
                    String[] sendCcEmails = sendCc.split(";");
                    for(String cc : sendCcEmails) {
                        ccEmails.add(cc);
                    }
                }
                request.setCcEmails(ccEmails);
                request.setRecipientEmails(recipientEmails);
                request.setSubject(subject);
                request.setMsgText(msg);
                String callResult = tspPlaInterface.sendInternalEmail(request);
                LOGGER.info(subject+"发送结果：" + callResult);
            }
        } catch (Exception e) {
            LOGGER.error("发送邮件出错：" + e.getMessage(), e);
        }
    }

}
