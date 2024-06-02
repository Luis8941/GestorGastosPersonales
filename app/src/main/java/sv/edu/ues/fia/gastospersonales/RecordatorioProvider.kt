package sv.edu.ues.fia.gastospersonales

class RecordatorioProvider {
    companion object {
        val recordatodioList = listOf<Recordatorio>(
            Recordatorio(
                "Agua",
                "05/06/2024",
                "$ 2.90"
            ),
            Recordatorio(
                "Luz",
                "09/06/2024",
                "$ 20.00"
            )
        )
    }
}