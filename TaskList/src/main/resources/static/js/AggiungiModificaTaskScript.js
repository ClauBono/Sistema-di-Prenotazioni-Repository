$(document).ready(function () {
    $('#btnInserisci').click(function (event) {
        event.preventDefault();

        // Ottieni i valori dei campi del form
        let nomeVal = $('#nome').val();
        let descrizioneVal = $('#descrizione').val();
        let dataScadenzaVal = $('#dataScadenza').val();

        // Verifica se tutti i campi sono stati compilati
        if (nomeVal && descrizioneVal && dataScadenzaVal) {
            // Crea l'oggetto FormData
            let formData = {
                nome: nomeVal,
                descrizione: descrizioneVal,
                dataScadenza: dataScadenzaVal
            };

            // Invia i dati del form tramite AJAX
            $.ajax({
                type: 'POST',
                url: $('#form').attr('action'),
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {
                    $('#form').hide();
                    $('#inserimentoMessage').show().text(response); // Mostra il messaggio di successo
                    console.log(response);
                },
                error: function (error) {
                    console.log(error);
                    alert("Errore durante l'invio dei dati. Si prega di riprovare.");
                }
            });
        } else {
            // Se almeno uno dei campi è vuoto, mostra un messaggio di avviso
            alert("Compilare tutti i campi per completare l'inserimento!");
        }
    });
});

$(document).ready(function () {

    $('#btnModifica').click(function (event) {

        event.preventDefault();

        // Creo nuove variabili e ottengo i valori dei campi
        let nomeVal = $('#nome').val();
        let descrizioneVal = $('#descrizione').val();
        let dataScadenzaVal = $('#dataScadenza').val();

        // Verifica se tutti i campi sono stati compilati
        if (nomeVal && descrizioneVal && dataScadenzaVal) {

            let formData = {
                nome: nomeVal,
                descrizione: descrizioneVal,
                dataScadenza: dataScadenzaVal
            };



            $.ajax({
                type: 'PATCH',
                url: $('#form').attr('action'),
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {


                    $('#form').hide();


                    $('#modificaMessage').show();

                    console.log(response);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }else {

            // Se almeno uno dei campi è vuoto, mostra un messaggio di avviso o fai qualche altra azione
            alert(" Completa tutti i campi per completare la modifica! ");
        }
    });
});
