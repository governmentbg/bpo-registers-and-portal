--liquibase formatted sql

--changeset ndimov:0103 splitStatements:false

UPDATE emails.email_template SET name = 'template', name_en = null, subject = 'Съобщение за докладвана грешка/ Notification of a Reported Error', text = e'<div>
    <div style="margin-right: 231px;">
        <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
            <br>
            <table style="margin:0;min-width:640px;padding:0;text-align:center;width:20%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                <tbody>
                    <tr>
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
                    </tr>
                    <tr>
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                            <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:1000px" cellspacing="0" cellpadding="0" border="0">
                                <tbody>
                                    <tr>
                                        <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                            <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                <tbody>
                                                    <tr>
                                                        <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 0px 5px" align="center">
                                                            <p><strong>Здравейте,<br><br>Докладвана е грешка</strong></p>
                                                            <p>
                                                                <br><em>Подател: </em><strong>{first_name} {last_name}</strong>
                                                                <br><em>Електронен адрес: </em><strong>{email}</strong>
                                                                <br>
                                                            </p>
                                                            <p>
                                                                <br><em>Номер: </em><strong>{number}</strong>
                                                                <br><em>Тип: </em><strong>{type}</strong><br><br>
                                                            </p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
                                                            <p><br><em>Описание:</em><br><br></p>
															<div style="
																	max-width: 800px;
																	min-width: 200px;
																	text-align: justify;">
															    <strong>
                                                                    {description}
                                                                </strong>
                                                            </div>
                                                            <p><br><br></p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
														</td>
                                                    </tr>
													<tr>
													    <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:0px 5px 15px 5px" align="center">
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
															<p><strong>Hello,<br><br>An Error Has Been Reported</strong></p>
                                                            <p>
                                                                <br><em>Sender: </em><strong>{first_name} {last_name}</strong>
                                                                <br><em>Email: </em><strong>{email}</strong>
                                                                <br>
                                                            </p>
                                                            <p>
                                                                <br><em>Number: </em><strong>{number}</strong>
                                                                <br><em>Type: </em><strong>{type}</strong><br><br>
                                                            </p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
                                                            <p><br><em>Description:</em><br><br></p>
															<div style="
																	max-width: 800px;
																	min-width: 200px;
																	text-align: justify;">
															    <strong>
                                                                    {description}
                                                                </strong>
                                                            </div>
                                                            <p><br><br></p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
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
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
                    </tr>
						<td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1;padding:10px 0 0px 0">
							<div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg"><em>portal.bpo.bg</em></a><br></div>
						</td>
                    <tr>
                       <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1;padding:10px 0 30px 0">
                            <div>This message was automatically generated by&nbsp;<a href="https://portal.bpo.bg"><em>portal.bpo.bg</em></a><br></div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>', created_date = '2025-02-11 11:52:05.118412 +00:00', last_update_date = '2025-03-18 11:00:22.263000 +00:00', is_html = true, user_create = null, user_last_update = null, params = null, notification_action = 'ErrorMessageAction' WHERE id = 'ERROR_MESSAGE';
UPDATE emails.email_template SET name = 'template', name_en = null, subject = 'Съобщение за подадено предложение / Notification of a Submitted Feedback', text = e'<div>
    <div style="margin-right: 231px;">
        <div bgcolor="#fafafa" style="background-color:#fafafa;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;height:100%;margin-bottom:0 !important;margin-left:0 !important;margin-right:0 !important;margin-top:0 !important;min-width:640px;padding:0;text-align:center;width:100%">
            <br>
            <table style="margin:0;min-width:640px;padding:0;text-align:center;width:20%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fafafa">
                <tbody>
                    <tr>
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
                    </tr>
                    <tr>
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif">
                            <table style="border-collapse:separate;border-spacing:0;margin:0 auto 0 auto;width:1000px" cellspacing="0" cellpadding="0" border="0">
                                <tbody>
                                    <tr>
                                        <td style="border:1px solid #ededed;border-radius:3px;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;overflow:hidden;padding:18px 25px 18px 25px" bgcolor="#fff" align="left">
                                            <table style="border-collapse:separate;border-spacing:0;width:100%" cellspacing="0" cellpadding="0" border="0">
                                                <tbody>
                                                    <tr>
                                                        <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:15px 5px 0px 5px" align="center">
                                                            <p><strong>Здравейте,<br><br>Подадено е предложение</strong></p>
                                                            <p>
                                                                <br><em>Подател: </em><strong>{first_name} {last_name}</strong>
                                                                <br><em>Електронен адрес: </em><strong>{email}</strong>
                                                                <br><br>
                                                            </p>
                                                            </p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
                                                            <p><br><em>Описание:</em><br><br></p>
															<div style="
																	max-width: 800px;
																	min-width: 200px;
																	text-align: justify;">
															    <strong>
                                                                    {description}
                                                                </strong>
                                                            </div>
                                                            <p><br><br></p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
														</td>
                                                    </tr>
													<tr>
													    <td style="color:#333;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:15px;font-weight:400;line-height:1.4;padding:0px 5px 15px 5px" align="center">
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
															<p><strong>Hello,<br><br>A feedback has been submitted</strong></p>
                                                            <p>
                                                                <br><em>Sender: </em><strong>{first_name} {last_name}</strong>
                                                                <br><em>Email: </em><strong>{email}</strong>
                                                                <br><br>
                                                            </p>
                                                            </p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
                                                            <p><br><em>Description:</em><br><br></p>
															<div style="
																	max-width: 800px;
																	min-width: 200px;
																	text-align: justify;">
															    <strong>
                                                                    {description}
                                                                </strong>
                                                            </div>
                                                            <p><br><br></p>
                                                            <hr color="#dcdcdc" size="1px" align="center" />
															<div style="color:rgba(0,0,0,0.01);width:0;height:0">&nbsp;</div>
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
                        <td style="font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:4px;height:4px;line-height:4px" bgcolor="#134194"><br></td>
                    </tr>
						<td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1;padding:10px 0 0px 0">
							<div>Това съобщение е автоматично генерирано от&nbsp;<a href="https://portal.bpo.bg"><em>portal.bpo.bg</em></a><br></div>
						</td>
                    <tr>
                       <td style="color:#5c5c5c;font-family:\'helvetica neue\' , \'helvetica\' , \'arial\' , sans-serif;font-size:13px;line-height:1;padding:10px 0 30px 0">
                            <div>This message was automatically generated by&nbsp;<a href="https://portal.bpo.bg"><em>portal.bpo.bg</em></a><br></div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>', created_date = '2025-02-11 11:52:05.118412 +00:00', last_update_date = '2025-03-18 11:00:22.263000 +00:00', is_html = true, user_create = null, user_last_update = null, params = null, notification_action = 'FeedbackAction' WHERE id = 'FEEDBACK';
