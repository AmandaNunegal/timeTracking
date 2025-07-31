$(document).ready(function () {
    $('#tkTable').DataTable({
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.5/i18n/es-ES.json'
        },
        order: [[3, 'desc']]
    });
});