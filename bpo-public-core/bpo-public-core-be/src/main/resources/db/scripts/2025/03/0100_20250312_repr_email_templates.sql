--liquibase formatted sql

--changeset iborisov:0100 splitStatements:false

alter table emails.email_notification_action add column notify_bcc_emails text;
insert into emails.email_notification_action (code, name, name_en, notify_emails, notify_bcc_emails) values ('RepresentativeRequestSuccessAction', 'RepresentativeRequestSuccessAction', 'RepresentativeRequestSuccessAction', null, 'services@bpo.bg');
insert into emails.email_notification_action (code, name, name_en, notify_emails, notify_bcc_emails) values ('RepresentativeRequestErrorAction', 'RepresentativeRequestErrorAction', 'RepresentativeRequestErrorAction', null, 'portal@bpo.bg,services@bpo.bg');

insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('REPRESENTATIVE_REQUEST_SUCCESS','template','Ново електронно заявление {entry_num} към портала за електронни административни услуги на Патентно Ведомство',true,
        '<div>
             <div style="margin-right: 231px;">
                 <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
                     <br>
                     <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                         <tbody>
                             <tr>
                                 <td style="font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>

                             </tr>
                             <tr>
                                 <td style="font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif">
                                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                                         <tbody>
                                             <tr>
                                                 <td style="border:1px solid #ededed;border-radius:3px;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                                     <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                         <tbody>
                                                             <tr>
                                                                 <td style="color:#333;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                                                     <p>Здравейте {applicant_name}<br><br>Регистрирано е ново електронно заявление <strong>{entry_num}</strong> към портала за електронни административни услуги на Патентно Ведомство.</p>

                                                                     <hr color="grey" size="1px" align="center" />
                                                                     <p>
                                                                         <br>Номер на преписка: <strong>{entry_num}</strong>
                                                                         <br><br>
                                                                     </p>
                                                                     <hr color="grey" size="1px" align="center" />
                                                                 <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div></td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                 </td>
                                             </tr>
                                         </tbody>
                                     </table>
                                 </td>
                             </tr>
                             <tr>
                                 <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                 </div>
             </div>
         </div>', 'RepresentativeRequestSuccessAction');

insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('REPRESENTATIVE_REQUEST_ERROR','template','Ново електронно заявление {entry_num} към портала за електронни административни услуги на Патентно Ведомство',true,
        '<div>
             <div style="margin-right: 231px;">
                 <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
                     <br>
                     <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                         <tbody>
                             <tr>
                                 <td style="font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>

                             </tr>
                             <tr>
                                 <td style="font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif">
                                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                                         <tbody>
                                             <tr>
                                                 <td style="border:1px solid #ededed;border-radius:3px;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                                     <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                         <tbody>
                                                             <tr>
                                                                 <td style="color:#333;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                                                     <p>
                                                                     Здравейте,<br><br>Регистрирано е <b>{service_type}</b> от <b>{applicant_name}</b> към портала за електронни административни услуги на Патентно Ведомство.<br>Заявлението е записано под номер: <b>{entry_num}</b>, но поради проблем при комуникацията с деловодната система, приемането му все още не е приключило.
                                                                     </p>

                                                                     <hr color="grey" size="1px" align="center" />
                                                                     <p>
                                                                         <b>Моля не подавайте заявлението отново. След като бъде заведено в деловодната система, ще получите допълнителна информация за успешното приемане на заявлението.</b>
                                                                         <br>
                                                                     </p>
                                                                     <hr color="grey" size="1px" align="center" />
                                                                 <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div></td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                 </td>
                                             </tr>
                                         </tbody>
                                     </table>
                                 </td>
                             </tr>
                             <tr>
                                 <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                 </td>
                             </tr>
                         </tbody>
                     </table>
                 </div>
             </div>
         </div>', 'RepresentativeRequestErrorAction');