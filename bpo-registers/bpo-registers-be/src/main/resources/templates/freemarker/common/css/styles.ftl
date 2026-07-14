<style type="text/css">

    * {
        font-family: 'Arial Unicode MS', sans-serif;
    }

    @page {
        font-family: 'Arial Unicode MS', sans-serif;
        @bottom-right {
            font-size: 10pt;
            counter-increment: page;
            content: counter(page) "/" counter(pages);
        }
        @bottom-left {
            font-size: 10pt;
            counter-increment: page;
            content: "${.now?string("dd.MM.yyyy HH:mm:ss")}";
        }
    }

    body {
        /*font-family: 'Noto Sans', sans-serif;*/
        font-size: small;
        margin: 0;
    }

    table {
        width: 100%;
        page-break-inside: auto;
        border-spacing: initial;
        border-collapse: collapse;
        table-layout: fixed;
        -fs-table-paginate: paginate; /*repeat header row on each page*/
    }

    thead {
        display: table-header-group;
        page-break-before: auto;
        page-break-inside: avoid;
        page-break-after: avoid;
    }

    tr {
        page-break-inside: avoid;
        page-break-after: auto;
    }

    th, td {
        border: 1px solid black;
        padding: 3px;
        text-align: left;
        word-wrap: break-word;
    }

    th {
        background: #cecece;
    }

    .page-unbreakable {
        page-break-inside: avoid;
    }
</style>