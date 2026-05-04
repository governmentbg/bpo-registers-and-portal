--liquibase formatted sql

--changeset raneva:0119 splitStatements:false

insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('INSERT_LIABILITY_NOTIFICATION_TEMPLATE','template','Ново задължение към заявка', true,
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
                                                                Здравейте,<br><br>Въведено e ново задължение към <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>


                                                            <p>Пълна справка за чакащите задължения към заявката можете да видите в портала за електронни услуги, като последвате горния линк. Ако не сте се идентифицирали в портала, ще трябва да го направите, за да получите достъп.&nbsp;<br><br><strong>За нови задължения: ако вече сте заплатили таксата по банков път, тя ще бъде отразена в системата след постъпването на сумата по сметката на ПВ и осчетоводяването й.</strong></p>
                                                            <hr color="grey" size="1px" align="center">



                                                            <p>Dear user,<br><br>New liability has been added in relation to <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>



                                                            <p>Full list of the pending liabilities for the application you can view in the portal for electronic services, by following the link above. If you have not identified yourself in the portal, you will have to do that to gain access.</p>
                                                            <p><strong>For new liabilities: If you have paid already the fee by bank transfer, it will be marked in the system as "paid" after the sum has been received in BPO''s account and it has been accounted.</strong></p>





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
                        <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                            <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a>. Моля, не отговаряйте.<br></div>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>', null);

insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('UPDATE_LIABILITY_NOTIFICATION_TEMPLATE','template','Променено задължение към заявка', true,
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
                                                                Здравейте,<br><br>Променено е задължение към <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>


                                                            <p>Пълна справка за чакащите задължения към заявката можете да видите в портала за електронни услуги, като последвате горния линк. Ако не сте се идентифицирали в портала, ще трябва да го направите, за да получите достъп.&nbsp;<br><br><strong>За нови задължения: ако вече сте заплатили таксата по банков път, тя ще бъде отразена в системата след постъпването на сумата по сметката на ПВ и осчетоводяването й.</strong></p>
                                                            <hr color="grey" size="1px" align="center">



                                                            <p>Dear user,<br><br>Liability has been updated in relation to <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>



                                                            <p>Full list of the pending liabilities for the application you can view in the portal for electronic services, by following the link above. If you have not identified yourself in the portal, you will have to do that to gain access.</p>
                                                            <p><strong>For new liabilities: If you have paid already the fee by bank transfer, it will be marked in the system as "paid" after the sum has been received in BPO''s account and it has been accounted.</strong></p>





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
                        <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                            <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a>. Моля, не отговаряйте.<br></div>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>', null);

insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('DELETE_LIABILITY_NOTIFICATION_TEMPLATE','template','Премахнато задължение към заявка', true,
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
                                                                Здравейте,<br><br>Премахнато е задължение към <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>


                                                            <p>Пълна справка за чакащите задължения към заявката можете да видите в портала за електронни услуги, като последвате горния линк. Ако не сте се идентифицирали в портала, ще трябва да го направите, за да получите достъп.&nbsp;<br><br><strong>За нови задължения: ако вече сте заплатили таксата по банков път, тя ще бъде отразена в системата след постъпването на сумата по сметката на ПВ и осчетоводяването й.</strong></p>
                                                            <hr color="grey" size="1px" align="center">



                                                            <p>Dear user,<br><br>Liability has been removed in relation to <a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>



                                                            <p>Full list of the pending liabilities for the application you can view in the portal for electronic services, by following the link above. If you have not identified yourself in the portal, you will have to do that to gain access.</p>
                                                            <p><strong>For new liabilities: If you have paid already the fee by bank transfer, it will be marked in the system as "paid" after the sum has been received in BPO''s account and it has been accounted.</strong></p>





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
                        <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                            <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a>. Моля, не отговаряйте.<br></div>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>', null);


insert into emails.email_template (id, name, subject, is_html, text, notification_action)
values ('DOC_NOTIFICATION_TEMPLATE','template','Нов(и) документ(и) към заявка', true,
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
                                                                Здравейте,<br><br>Имате нов(и) документ(и) към&nbsp;<a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>


                                                            <p>Текстовете на документите можете да видите в портала за електронни услуги, като последвате горния линк. Ако не сте се идентифицирали в портала, ще трябва да го направите, за да получите достъп.&nbsp;<br></p>
                                                            <hr color="grey" size="1px" align="center">



                                                            <p>Dear user,<br><br>You have new document(s) regarding&nbsp;<a href="{link_url}" target="_blank"><strong>{reference_number}</strong>
                                                                </a>
                                                            </p>



                                                            <p>The text of the document(s) you can view in the portal for electronic services, by following the link above. If you have not identified yourself in the portal, you will have to do that to gain access.</p>






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
                        <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                            <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a>. Моля, не отговаряйте.<br></div>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>', null);