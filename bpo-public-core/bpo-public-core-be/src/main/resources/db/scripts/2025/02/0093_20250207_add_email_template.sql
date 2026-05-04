--liquibase formatted sql

--changeset ndimov:0093 splitStatements:false
insert into emails.email_template (id,name,subject,is_html,text)
values ('FEEDBACK','template','Съобщение за подадено предложение',true,
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
                                                                    <p>Здравейте<br><br>Подадено е предложение</p>
                                                                    <p>
                                                                        <br>Подател: <strong>{first_name} {last_name}</strong>
                                                                        <br>Електронен адрес: <strong>{email}</strong>
                                                                        <br><br>
                                                                    </p>
                                                                    <hr color="grey" size="1px" align="center" />
                                                                    <p><br>Описание:<br><br></p>
                                                                    <div style="
                                                                            max-width: 800px;
                                                                            min-width: 200px;
                                                                            text-align: justify;">
                                                                        <strong>
                                                                            {description}
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
                                <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                    <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>');

insert into emails.email_template (id,name,subject,is_html,text)
values ('ERROR_MESSAGE','template','Съобщение за докладвана грешка',true,
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
                                                                    <p>Здравейте<br><br>Докладвана е грешка</p>
                                                                    <p>
                                                                        <br>Подател: <strong>{first_name} {last_name}</strong>
                                                                        <br>Електронен адрес: <strong>{email}</strong>
                                                                        <br>
                                                                    </p>
                                                                    <p>
                                                                        <br>Номер: <strong>{number}</strong>
                                                                        <br>Тип: <strong>{type}</strong><br><br>
                                                                    </p>
                                                                    <hr color="grey" size="1px" align="center" />
                                                                    <p><br>Описание:<br><br></p>
                                                                    <div style="
                                                                            max-width: 800px;
                                                                            min-width: 200px;
                                                                            text-align: justify;">
                                                                        <strong>
                                                                            {description}
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
                                <td style="color:#5c5c5c;font-family:''helvetica neue'' , ''helvetica'' , ''arial'' , sans-serif;font-size:13px;line-height:1.6;padding:25px 0 25px 0">
                                    <div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg">portal.bpo.bg</a><br></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>');