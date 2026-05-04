<#ftl encoding="UTF-8" />
<#escape x as x?html>
    <html>
    <head>
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
        <#include "../common/css/styles.ftl">
        <#include "../common/macros/label.ftl">
    </head>

    <body>
    <#include "../sections/heading.ftl"/>
    <#include "../sections/ipobjects/common/main_data.ftl"/>
    <#include "../sections/ipobjects/common/priorities_data.ftl"/>
    <#include "../sections/ipobjects/common/related_objects_data.ftl"/>
    <#include "../sections/ipobjects/common/publications_data.ftl"/>
    <#include "../sections/ipobjects/common/images_data.ftl"/>
    <#include "../sections/ipobjects/marklike/trademark/colours_data.ftl"/>
    <#include "../sections/ipobjects/marklike/trademark/description_data.ftl"/>
    <#include "../sections/ipobjects/common/attachments_data.ftl"/>
    <#include "../sections/ipobjects/marklike/common/vienna_classes_data.ftl"/>
    <#include "../sections/ipobjects/marklike/common/nice_classes_data.ftl"/>
    <#assign personType = "OWNER">
    <#include "../sections/ipobjects/common/persons_data.ftl"/>
    <#assign personType = "REPRESENTATIVE">
    <#include "../sections/ipobjects/common/persons_data.ftl"/>
    <#include "../sections/ipobjects/common/recordal_data.ftl"/>
    <#include "../sections/ipobjects/common/opposition_data.ftl"/>
    <#include "../sections/ipobjects/common/cancellation_data.ftl"/>
    <#include "../sections/ipobjects/common/legal_decisions_data.ftl"/>
    </body>
    </html>
</#escape>