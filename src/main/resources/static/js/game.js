"use strict";
import {byId, setText} from "./util.js";

const game = JSON.parse(sessionStorage.getItem("game"));
console.log(game);
const titel = `${game.titel}`;
setText("titel", titel);

const imgFoto = byId("foto");
imgFoto.alt = titel;
imgFoto.src = `images/${game.id}.jpg`;

const beschikbaar = (`${game.voorraad}`- `${game.gereserveerd}`)
setText("prijs", game.prijs);
setText("voorraad", game.voorraad);
setText("gereserveerd", game.gereserveerd);
setText("beschikbaar", beschikbaar);

const button = document.getElementById("mand");

if (beschikbaar === 0) {
    button.disabled = true;
}