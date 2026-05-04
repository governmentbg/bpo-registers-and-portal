--liquibase formatted sql

--changeset ggeorgiev:0101 splitStatements:false
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('PASSWORD_CHANGED', 'template', null, 'Промяна на парола/Password changed', e'<div>
   <div style="margin-right: 231px;">
      <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
         <br>
         <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
            <tbody>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
               </tr>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                           <tr>
                              <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                 <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                    <tbody>
                                       <tr>
                                          <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                             Уважаеми <strong>{name}</strong>,<br><br>Този имейл потвърждава, че вашата парола е променена.<br><br>С уважение,<br>Екипът на Патентното ведомство<br><br><br>
                                             <hr>
                                             Dear <strong>{name}</strong>,<br><br>This email confirms that your password has been changed.<br><br>Sincerely,<br>The team of the Patent Office<br>
                                             <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;<br></div>
                                          </td>
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
                  <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   </div>
</div>
', '2025-02-28 15:31:28.722000 +00:00', '2025-02-28 15:31:30.043000 +00:00', true, null, null, null, null);
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('EMAIL_VERIFICATION', 'template', null, 'Проверка на имейл/Email verification', e'<div>
   <div style="margin-right: 231px;">
      <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
         <br>
         <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
            <tbody>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
               </tr>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                           <tr>
                              <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                 <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                    <tbody>
                                       <tr>
                                          <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                             Здравейте, <strong>{name}</strong>,<br><br>Вашият акаунт в портала на Патентно ведомство е почти готов. Благодаря ви, че се включихте.<br>Моля, потвърдете своя имейл адрес, като щракнете върху следната връзка или като използвате кода за потвърждение по-долу: <a href="{verify_account_link}">линк за активиране на профила</a><br>Вашият код за потвърждение е: {verify_account_code}<br><br>С уважение,<br>Екипът на Патентно ведомство<br><br>
                                             <hr>
                                             Hi <strong>{name}</strong>,<br><br>Your account on the portal of the Patent Office is almost ready.<br>Please verify your email address by going to the following link or using the verification code below: <a href="{verify_account_link}">verification link</a><br>Your verification code is: {verify_account_code}<br><br>Sincerely,<br>The team of the Patent Office<br>
                                             <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;<br></div>
                                          </td>
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
                  <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   </div>
</div>
', '2025-02-28 14:43:59.822000 +00:00', '2025-02-28 14:44:01.800000 +00:00', true, null, null, null, null);
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('NEW_INCOMING_CORRESP', 'template', null, 'Нова входяща кореспонденция/New incoming correspondence', e'<div>
   <div style="margin-right: 231px;">
      <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
         <br>
         <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
            <tbody>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
               </tr>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                           <tr>
                              <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                 <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                    <tbody>
                                       <tr>
                                          <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                             Уважаеми <strong>{name}</strong>,<br><br>Получихте нова кореспонденция в портала на Патентното ведомство.<br>Можете да влезете и да проверите там.<br><br>С уважение,<br>Екипът на Патентното ведомство<br><br>
                                             <hr>
                                             <br>Dear <strong>{name}</strong>,<br><br>You have received a new correspondence in Patent office portal.<br>You can log in and check it there.<br><br>Sincerely,<br>The team of the Patent Office<br>
                                             <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;<br></div>
                                          </td>
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
                  <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   </div>
</div>
', '2025-02-28 13:41:28.997783 +00:00', '2025-02-28 15:41:20.876000 +00:00', true, null, null, null, null);
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('APPLICATION_STUDY_ERROR', 'template', null, 'Ново електронно заявление към портала за електронни административни услуги на Патентно Ведомство', e'<div>
            <div style="margin-right: 231px;">
                <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
                    <br>
                    <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                        <tbody>
                            <tr>
                                <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>

                            </tr>
                            <tr>
                                <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                                    <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                                        <tbody>
                                            <tr>
                                                <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                                    <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                        <tbody>
                                                            <tr>
                                                                <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">

                                                                        <br>Подател: <strong>{applicant_name} </strong>



                                                                    <hr color="grey" size="1px" align="center" />
                                                                    <p><br>Описание:<br><br></p>
                                                                    <div style="
                                                                            max-width: 800px;
                                                                            min-width: 200px;
                                                                            text-align: justify;">
                                                                        <strong>
                                                                            Здравейте {applicant_name},<br><br>Поради техническа грешка в системата, вашето заявление подлежи на допълнителна обработка.<br><b>Моля не подавайте заявлението отново. След като бъде обработено, ще получите допълнителна информация за входящия номер.</b><br><br>----------------------------------------------------<br>Това съобщение е генерирано автоматично. Моля, не изпращайте отговор.<br><br>Поздрави,<br>Екипът на Патентно Ведомство
                                                                        </strong>
                                                                    </div>
                                                                    <p><br><br></p>
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
                                <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                    <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>', '2025-02-12 13:54:23.117000 +00:00', '2025-02-12 13:54:26.574000 +00:00', true, null, null, null, null);
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('APPLICATION_STUDY_SUCCESS', 'template', null, 'Ново електронно заявление към портала за електронни административни услуги на Патентно Ведомство', e'<div>
            <div style="margin-right: 231px;">
                <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
                    <br>
                    <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                        <tbody>
                            <tr>
                                <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>

                            </tr>
                            <tr>
                                <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                                    <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                                        <tbody>
                                            <tr>
                                                <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                                    <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                        <tbody>
                                                            <tr>
                                                                <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">

                                                                        <br>Подател: <strong>{applicant_name} </strong>



                                                                    <hr color="grey" size="1px" align="center" />
                                                                    <p><br>Описание:<br><br></p>
                                                                    <div style="
                                                                            max-width: 800px;
                                                                            min-width: 200px;
                                                                            text-align: justify;">
                                                                        <strong>
                                                                            Здравейте {applicant_name},<br><br>Регистрирано е ново електронно заявление <b>{entry_num}</b> към портала за електронни административни услуги на Патентно Ведомство.<br><br>Номер на преписка: <b>{entry_num}</b><br><br>----------------------------------------------------<br>Това съобщение е генерирано автоматично. Моля, не изпращайте отговор.<br><br>Поздрави,<br>Екипът на Патентно Ведомство
                                                                        </strong>
                                                                    </div>
                                                                    <p><br><br></p>
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
                                <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                    <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>', '2025-02-12 13:54:22.160000 +00:00', '2025-02-12 13:54:22.508000 +00:00', true, null, null, null, null);
INSERT INTO emails.email_template (id, name, name_en, subject, text, created_date, last_update_date, is_html, user_create, user_last_update, params, notification_action) VALUES ('PASSWORD_RESET', 'template', null, 'Забравени данни за вход/Forgotten login details', e'<div>
   <div style="margin-right: 231px;">
      <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
         <br>
         <table style="margin:0;min-width:640px;padding:0;text-align:center;width:100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
            <tbody>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
               </tr>
               <tr>
                  <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                     <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:640px" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                           <tr>
                              <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                 <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                    <tbody>
                                       <tr>
                                          <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 15px 5px" align="center">
                                             Уважаеми <strong>{name}</strong>,<br><br>Поискахте промяна на забравена парола за потребител <strong>{username}</strong>.<br>Моля, щракнете върху връзката по-долу, за да промените паролата си.<br><br>
                                             <a href="{password_reset_link}">линк за смяна на парола</a>
                                             <br><br>Ако тази заявка е изпратена от някой друг, а не от вас, можете да игнорирате това съобщение и да продължите да използвате текущата си парола.<br><br>С уважение,<br>Екипът на Патентното ведомство<br><br>
                                             <hr>
                                             <br>Dear <strong>{name}</strong>,<br><br>You have recently requested to reset your password for {username}.<br>Please click on the link below to change your password.<br><br><a href="{password_reset_link}">password change link</a><br><br>If this request was sent by someone else and not by you, you can ignore this message and continue to use your current password.<br><br>Sincerely,<br>The team of the Patent Office<br>
                                             <div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;<br></div>
                                          </td>
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
                  <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                     <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   </div>
</div>
', '2025-02-28 15:02:54.364000 +00:00', '2025-02-28 15:02:55.053000 +00:00', true, null, null, null, null);
