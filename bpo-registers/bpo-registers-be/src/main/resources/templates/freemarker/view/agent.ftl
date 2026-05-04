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
    <#include "../sections/representatives/common/main_data.ftl"/>
    <#include "../sections/representatives/common/relations_data.ftl"/>
    <#include "../sections/representatives/common/history_data.ftl"/>
    </body>
    </html>
</#escape>