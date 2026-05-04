<#ftl encoding="UTF-8" />
<#escape x as x?html>
    <html>
    <head>
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
        <#include "../common/css/styles.ftl">
        <#include "../common/macros/label.ftl">
        <style type="text/css">
            @page {
                size: A4 landscape;
            }
        </style>
    </head>

    <body>
    <#include "../sections/heading.ftl"/>

    <#if filter?has_content>
        <#include "filter/agent_filter_data.ftl">
    </#if>

    <@sectionHeading 'l.section.results'/>

    <#if results?has_content>
        <#assign index = 0>
        <div>
            <table class="table table-striped mt-3">
                <thead>
                <tr>
                    <#include "../table_columns/headers/agent_headers.ftl"/>
                </tr>
                </thead>
                <tbody>
                <#list results as object>
                    <#assign index = index+1>
                    <tr>
                        <td>
                            ${index}
                        </td>
                        <#include "../table_columns/columns/agent_columns.ftl"/>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        <@label "l.no.data"/>
    </#if>
    </body>
    </html>
</#escape>