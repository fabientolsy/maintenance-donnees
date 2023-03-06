<?php
    include "connexion.php";
    
    print_r($_POST);
    $planete = $_POST ["nom"];
    $etoile = $_POST ["etoile"];
    $distance = $_POST ["distance"];

    $SQL_AJOUTER_EXOPLANETE = "INSERT into exoplanete (planete, etoile, distance) VALUES ('$planete', '$etoile','$distance')";

    print_r($SQL_AJOUTER_EXOPLANETE);

    $requete = $basededonnees->prepare($SQL_AJOUTER_EXOPLANETE);
    $requete -> execute();

    print_r("Ajout reusist !");
?>