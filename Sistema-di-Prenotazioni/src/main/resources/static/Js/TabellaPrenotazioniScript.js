$(document).ready(function () {

    // Inizializzazione della tabella principale
    let table = $('#tabellaPrenotazioni').DataTable({
        "ajax": {
            url: "/Le mie Prenotazioni/ottieni le prenotazioni per utente",
            dataSrc: ""
        },
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Italian.json"
        },
        "columns": [
            {"data": "id"},
            {"data": "tipoServizio"},
            {"data": "dataPrenotazione"},
            {"data": "statoPrenotazione.stato"},
            {"data": "statoPrenotazione.descrizione"},
        ],
        "columnDefs": [
            { targets: [0], orderData: [0, 1] },
            { targets: [1], orderData: [1, 0] },
            { targets: [2], orderData: [2, 0] },
            { targets: [3], orderData: [2, 0] },
            { targets: [4], orderable: false },
        ],
        "paging": true, // paginazione
        "info": true, // informazioni sulla pagina
        "searching": true, // Abilita la barra di ricerca
        "scrollCollapse": true, // Abilita lo scrolling
        "scrollY": '70%' // Imposta l'altezza dello scrolling
    });


    // Evento click sulla cella delle colonne dalla prima alla quarta
    $('#tabellaPrenotazioni tbody').on('click', 'td:nth-child(-n+4)', function () {

        // Ottiene i dati della riga selezionata
        let rowData = table.row($(this).closest('tr')).data();

        window.location.href = '/ModificaPrenotazione/' + rowData.id;

    });

});
