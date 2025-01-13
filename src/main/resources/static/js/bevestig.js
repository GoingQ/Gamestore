"use strict";
import {byId, setText, toon, verberg} from "./util.js";

const klant = JSON.parse(sessionStorage.getItem("klant"));
const naam = `${klant.familienaam} ${klant.voornaam}`;
const aantal = parseInt(sessionStorage.getItem("mandjeTotaal"));
const inhoud = JSON.parse(sessionStorage.getItem("x"));

setText("bevestiging",aantal + " game(s) voor " + naam);
byId("bevestig").onclick = async function () {
    verbergFouten()
    const reservatie = [];
    for (const selected of inhoud) {
        const reservation = {
            klantId: `${klant.id}`,
            gameId: `${selected.id}`
        }
        reservatie.push(reservation);
    }
    const response = await fetch("reservaties",
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(reservatie)
        });
    if(response.ok) {
        const ul = byId("reservatieGeslaagd");
        for (const selected of inhoud) {
            const li = document.createElement("li");
            if(selected.voorraad > selected.gereserveerd) {
                li.innerText = `${selected.titel}` + ": OK";
                ul.appendChild(li);
            } else {
                li.innerText = `${selected.titel}` + ": Uitverkocht";
                ul.appendChild(li);
            }
        }
        byId("link").remove();
        byId("bevestig").disabled = true;
        sessionStorage.clear();
    } else {
        switch (response.status) {
            case 404:
                toon("nietGevonden");
                break;
            case 409:
                const responseBody = await response.json();
                setText("conflict", responseBody.message);
                toon("conflict");
                break;
            default:
                toon("storing");
        }
    }
}
function verbergFouten() {
    verberg("nietGevonden");
    verberg("conflict");
    verberg("storing");
}