<?php
    header("Access-Control-Allow-Origin: *");
    header ("Content-Type:text/xml");
    echo '<?xml version="1.0" encoding="UTF-8"?>';
    include "connexion.php";
    $SQL_EXOPLANETES = "SELECT * from exoplanete";
    $requete = $basededonnees->prepare($SQL_EXOPLANETES);
    $requete->execute();
    $planetes = $requete->fetchAll(PDO::FETCH_OBJ);

    //print_r($planetes);
    ?>

    <planetes>

    <?php

    foreach($planetes as $planete)
    {
        ?>
        <planete>
            <nom><?=$planete->planete?></nom>
            <etoile><?=$planete->etoile?></etoile>
            <typeEtoile><?=$planete->typeEtoile?></typeEtoile>
            <masse><?=$planete->masse?></masse>
            <rayon><?=$planete->rayon?></rayon>
            <flux><?=$planete->flux?></flux>
            <temperature><?=$planete->temperature?></temperature>
            <periode><?=$planete->periode?></periode>
            <distance><?=$planete->distance?></distance>
            <zone><?=$planete->zone?></zone>
            <ist><?=$planete->ist?></ist>
            <sph><?=$planete->sph?></sph>
            <hzd><?=$planete->hzd?></hzd>
            <hzc><?=$planete->hzc?></hzc>
            <hza><?=$planete->hza?></hza>
            <pClasse><?=$planete->pClasse?></pClasse>
            <hClasse><?=$planete->hClasse?></hClasse>
            <phi><?=$planete->phi?></phi>
            <status><?=$planete->status?></status>
            <decouverte><?=$planete->decouverte?></decouverte>
        </planete>

    <?php
    }
    ?>

    </planetes>  