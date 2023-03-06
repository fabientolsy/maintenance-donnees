<?php
    include "connexion.php";

    print_r($_POST);
    $id = $_POST ["id"]

    $SQL_SUPPRIMER_EXOPLANETE = "DELETE from exoplanete WHERE id = $id ";

    print_r($SQL_SUPPRIMER_EXOPLANETE);

    $requete = $basededonnees->prepare($SQL_SUPPRIMER_EXOPLANETE);
    $requete -> execute();

    print_r("Supression reusist !");
?>