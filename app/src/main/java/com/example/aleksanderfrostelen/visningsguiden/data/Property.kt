package com.example.aleksanderfrostelen.visningsguiden.data

data class Property(
    val address: String,
    val livingArea: Int,
    val rooms: Int,
    val storage: Boolean,
    val description: String?,
    val cost: Int
) {
    val listOfRooms = mutableListOf<Room>()

    init {
        listOfRooms.add(Room(RoomType.KITCHEN, "Kök"))
        listOfRooms.add(Room(RoomType.BATHROOM, "Badrum"))
        listOfRooms.add(Room(RoomType.HALLWAY, "Hall"))

        if (rooms == 1) {
            listOfRooms.add(Room(RoomType.BEDROOM, "Sovrum"))
        } else if (rooms == 2) {
            listOfRooms.add(Room(RoomType.LIVINGROOM, "Vardagsrum"))
            listOfRooms.add(Room(RoomType.BEDROOM, "Sovrum 1"))
        } else {
            listOfRooms.add(Room(RoomType.LIVINGROOM, "Vardagsrum"))
            for (i in 1 until rooms) {
                listOfRooms.add(Room(RoomType.BEDROOM, "Sovrum $i"))
            }
        }
    }
}

data class Room(
    val type: RoomType,
    val title: String,
    var questions: List<UserQuestions> = listOf(),
    var userPoints: Int = 1

) {
    init {
        questions = type.generateQuestions()
    }
}

data class UserQuestions(
    val questionText: String,
    var checked: Boolean = false,
    var note: String = ""
)

enum class RoomType {
    BEDROOM,
    KITCHEN,
    BATHROOM,
    HALLWAY,
    LIVINGROOM;

    fun generateQuestions(): List<UserQuestions> {
        return when (this) {
            BEDROOM -> {
                listOf(
                    UserQuestions("Kan du få det tyst, mörkt och svalt här?"),
                    UserQuestions("Hur ser skick på golv, väggar och tak ut?"),
                    UserQuestions("Får dina möbler plats? Mät gärna så du vet om tex sängen får plats"),
                    UserQuestions("Hur ser förvaringsmöjligheterna ut?"),
                    UserQuestions("Vad finns utanför fönstret? Trafikerad väg, annan fastighet eller innegård?")
                )
            }
            KITCHEN -> {
                listOf(
                    UserQuestions("Vilken ålder och skick har köket? Kommer det behöva renoveras?"),
                    UserQuestions("Öppna alla luckor och lådor. Hur är skicket där?"),
                    UserQuestions("Testa alla vitvaror (kyl, frys, ugn, spis, diskmaskin, micro mm). Fungerar dem? Finns skydd under diskmaskin, kyl, frys."),
                    UserQuestions("Är planlösningen bra? Passar kökets utformning dig och dina behov t ex mängd förvaring?"),
                    UserQuestions("Testa alla kranar tryck och ev. läckage. Öppna under vasken, titta på avloppet. Ser allt bra ut, finns det tecken på fuktproblem?"),
                    UserQuestions("Finns det diskmaskin? Om inte, finns det möjlighet att montera en?"),
                    UserQuestions("Finns det möjligheter att ta ner eller öppna upp väggen mer?"),
                    UserQuestions("Luktar det konstigt?"),
                    UserQuestions("Kontrollera drag och typ av fläkt - vilka vädringsmöjligheter finns?"),
                    UserQuestions("Vilken typ av ugn och spis är monterad?"),
                    UserQuestions("Finns det utrymme för källsortering?")
                )

            }
            BATHROOM -> {
                listOf(
                    UserQuestions("Vilken ålder och skick har badrummet? Kommer det behöva renoveras?"),
                    UserQuestions("Om skicket är bra, när renoverades det? Utfördes arbetet av auktoriserade hantverkare?"),
                    UserQuestions("Passar badrummet dina behov? Glöm t.ex. inte förvaring och rummets uppbyggnad"),
                    UserQuestions("Finns det bra ventilation?"),
                    UserQuestions("Luktar det konstigt? Ser du tecken på mögelskador såsom unken lukt, färgskiftningar, svarta prickar i kakelfogar eller bubblor i golvet?"),
                    UserQuestions("Fungerar ev. golvvärme och/eller handdukstork?"),
                    UserQuestions("Är det bra tryck och avrinning i dusch/tvättställ? Titta efter eventuella läckage."),
                    UserQuestions("Finns det tvättmaskin/torktumlare? Om inte, går det att koppla in?"),
                    UserQuestions("Kontrollera ytskikt (ålder och om det är vattenavvisande)"),
                    UserQuestions("Är golvbrunnen enligt nya riktlinjer?"),
                    UserQuestions("Finns det brandvarnare?")
                )
            }
            HALLWAY -> {
                listOf(
                    UserQuestions("Finns det goda förvaringsmöjligheter?"),
                    UserQuestions("Lyft på ev. dörrmattor och kolla skick på golvet"),
                    UserQuestions("Finns det säkerhetsdörr med titthål?"),
                    UserQuestions("Låter det mycket från trapphuset när dörren är stängd?"),
                    UserQuestions("Hur ser låset ut?"),
                    UserQuestions("Finns det larm?")

                    )
            }
            LIVINGROOM -> {
                listOf(
                    UserQuestions("Möblerbarheten - kommer dina möbler få plats? Finns det ytor du inte kommer kunna möblera?"),
                    UserQuestions("Är det lätt att vädra ur?"),
                    UserQuestions("Ljusinsläpp - åt vilket håll är detta åt?"),
                    UserQuestions("Var finns uttag för TV?"),
                    UserQuestions("Mät gärna befintliga möbler för att få en bild av möblerings-möjligheterna")
                )
            }
        }
    }
}