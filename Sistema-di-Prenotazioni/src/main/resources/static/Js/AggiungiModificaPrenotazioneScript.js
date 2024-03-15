//Logica della prima form quella di inserimento
$(document).ready(function () {

    $('#btnInserisci').click(function (event) {

        event.preventDefault();

        // Creo nuove variabili e ottengo i valori dei campi
        let nomeClienteVal = $('#nomeCliente').val();
        let dataPrenotazioneVal = $('#dataPrenotazione').val();
        let tipoServizioVal = $('#tipoServizio').val();

        // Verifica se tutti i campi sono stati compilati
        if (nomeClienteVal && dataPrenotazioneVal && tipoServizioVal) {

            let formData = {
                nomeCliente: nomeClienteVal,
                dataPrenotazione: dataPrenotazioneVal,
                tipoServizio: tipoServizioVal
            };

            $.ajax({
                type: 'POST',
                url: $('#form').attr('action'),
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {

                    $('#form').hide();

                    $('#inserimentoMessage').show();

                    console.log(response);
                },
                error: function (error) {

                    console.log(error);
                }
            });
        } else {

            // Se almeno uno dei campi è vuoto, mostra un messaggio di avviso o fai qualche altra azione
            alert(" Completa tutti i campi per completare l'inserimento! ");
        }
    });
});



//Logica della seconda form quella di modifica

$(document).ready(function () {

    $('#btnModifica').click(function (event) {

        event.preventDefault();

        // Creo nuove variabili e ottengo i valori dei campi
        let nomeClienteVal = $('#nomeCliente').val();
        let dataPrenotazioneVal = $('#dataPrenotazione').val();
        let tipoServizioVal = $('#tipoServizio').val();

        // Verifica se tutti i campi sono stati compilati
        if (nomeClienteVal && dataPrenotazioneVal && tipoServizioVal) {

            let formData = {
                nomeCliente: nomeClienteVal,
                dataPrenotazione: dataPrenotazioneVal,
                tipoServizio: tipoServizioVal
            };

            console.log(formData)

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
        } else {

            // Se almeno uno dei campi è vuoto, mostra un messaggio di avviso o fai qualche altra azione
            alert(" Completa tutti i campi per completare la modifica! ");
        }
    });

});