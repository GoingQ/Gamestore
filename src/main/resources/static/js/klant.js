"use strict";
import {byId, toon, verberg, verwijderChildElementenVan} from "./util.js";
byId("zoek").onclick = async function () {
    verbergKlantenEnFouten();
    const woordInput = byId("woord");
    if (woordInput.checkValidity()) {
        findByWoord(woordInput.value);
    } else {
        toon("woordFout");
        woordInput.focus();
    }
}
function verbergKlantenEnFouten() {
    verberg("klantenTable");
    verberg("woordFout");
    verberg("storing");
}
async function findByWoord(woord) {
    const response = await fetch(`klanten?naamBevat=${woord}`);
    if (response.ok) {
        const klanten = await response.json();
        toon("klantenTable");
        const klantenBody = byId("klantenBody");
        verwijderChildElementenVan(klantenBody);
        for (const klant of klanten) {
            const tr = klantenBody.insertRow();
            const hyperlink = document.createElement("a");

            hyperlink.href = "bevestig.html";
            hyperlink.innerText = `${klant.familienaam} ${klant.voornaam}`;
            const cellOne = tr.insertCell();
            cellOne.appendChild(hyperlink);
            tr.insertCell().innerText = klant.straatNummer;
            tr.insertCell().innerText = klant.postcode;
            tr.insertCell().innerText = klant.gemeente;

            hyperlink.onclick = function () {
                sessionStorage.setItem("klant", JSON.stringify(klant));
            }
        }
    } else {
        toon("storing");
    }
}