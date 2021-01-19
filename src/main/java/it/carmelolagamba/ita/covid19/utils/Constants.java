package it.carmelolagamba.ita.covid19.utils;

public class Constants {

    private static String baseUrlGithubRepoRaw = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/";
    private static String baseFolderNazione = "dati-andamento-nazionale/";
    private static String baseNameFileNazione = "dpc-covid19-ita-andamento-nazionale-";
    private static String baseFolderProvincia = "dati-province/";
    private static String baseNameFileProvincia = "dpc-covid19-ita-province-";
    private static String baseFolderRegioni = "dati-regioni/";
    private static String baseNameFileRegioni = "dpc-covid19-ita-regioni-";

    // Full path
    public static String baseUrlNazione = String.join("", baseUrlGithubRepoRaw, baseFolderNazione, baseNameFileNazione);
    public static String baseUrlRegioni = String.join("", baseUrlGithubRepoRaw, baseFolderRegioni, baseNameFileRegioni);
    public static String baseUrlProvincia = String.join("", baseUrlGithubRepoRaw, baseFolderProvincia, baseNameFileProvincia);
    public static String defaultExtension = ".csv";

    public static String folderNazioni = "data/dati-andamento-nazionale";
    public static String folderProvincia = "data/dati-province";
    public static String folderRegioni = "data/dati-regioni";
    public static String folderVaccini = "data/dati-vaccini";

    public static String nazioneText = "nazione";
    public static String provinciaText = "province";
    public static String regioneText = "regioni";

}
