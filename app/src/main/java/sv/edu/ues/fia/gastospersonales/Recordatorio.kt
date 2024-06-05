package sv.edu.ues.fia.gastospersonales

data class Recordatorio (
    var idRecordatorio:Int,
    var idFrecuencia:Int,
    var nombreR: String,
    var fecha:String,
    var hora:String,
    var monto:Double,
)