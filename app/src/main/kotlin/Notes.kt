import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font

class Notes: VBox() {
    // Variables
    var important = false
    var title = Label("Title")
    var body = Label("Body")
    var selected = false
    var id = 0

    init {
        setPrefSize(150.0,200.0)
        background = (Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)))
        spacing = 10.0
        padding = Insets(10.0)
        title = Label(generateTitle())
        title.font = Font.font(14.0)
        body = Label(generateBody())
        body.isWrapText = true
        body.font = Font.font(14.0)
        children.add(title)
        children.add((body))

        var num = (1..5).random()
        if (num == 1) {
            important = true
        }
        if (important) {
            background = (Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)))
        }
    }

    // Helper functions
    fun setTitleBody(s1: String, s2: String) {
        children.remove(title)
        children.remove(body)
        title = Label(s1)
        title.font = Font.font(14.0)
        body = Label(s2)
        body.isWrapText = true
        body.font = Font.font(14.0)
        children.add(title)
        children.add(body)
    }

    fun isSelected(b: Boolean) {
        style = if (b) {
            "-fx-border-color: blue;"
        } else {
            "-fx-border-color: white;"
        }
    }

    fun findText(text: String): Boolean {
        return this.title.text.contains(text, true) || this.body.text.contains(text, true)
    }

    fun generateTitle(): String {
        val listOfTitles = listOf("Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Consect", "Adipis", "Elit",
            "Donec", "Vestib", "Metus", "In", "Leo", "Efficitur", "Auctor", "Nunc", "Vel", "Est", "Faucibus",
            "Ultricies", "Urna", "In", "Posuere", "Sem", "Sed", "Semper", "Iaculis", "Sapien", "Ac",
            "Ullamcorper", "Ante", "Aliquet")
        var num = (1..3).random()
        var i = 0
        var title = ""
        while (i < num) {
            title += listOfTitles[(0..listOfTitles.size-1).random()] + " "
            i++
        }
        return title
    }

    fun generateBody(): String {
        val listOfBodyText = listOf("Donec rutrum arcu et finibus eleifend. ",
            "Duis gravida est nec. ",
            "Proin elementum purus eget lectus. ",
            "Aenean quis arcu sed diam placerat feugiat. ",
            "Pellentesque tempus massa et tincidunt mollis. ",
            "Donec ut ex vehicula, feugiat ante vel. ",
            "Duis fringilla ligula at sem molestie, in dictum. ",
            "In tincidunt diam a arcu luctus. ",
            "Nam vel elit vel elit malesuada lacinia. ",
            "Phasellus interdum orci et diam hendrerit. ",
            "Sed porttitor magna et felis pharetra. ",
            "Vestibulum condimentum lacus vulputate diam. ",
            "Nulla a urna tristique, facilisis ante ac. ",
            "Nunc vel nisi nec odio facilisis posuere. ",
            "Morbi luctus erat vitae augue. ",
            "Nunc faucibus est convallis finibus. ",
            "Sed eu nibh quis justo condimentum. ",
            "Morbi in lectus aliquam, porta neque accumsan. ",
            "Etiam vel tellus a dui rutrum. ",
            "Praesent condimentum nisi vel metus. ",
            "Aenean gravida odio sit amet porttitor blandit. ",
            "Sed consectetur lacus id metus suscipit malesuada. ",
            "Fusce gravida arcu vitae purus tristique. ",
            "Integer sit amet lacus id tellus. ",
            "Integer condimentum nibh nec laoreet posuere. ",
            "Etiam sit amet tortor tincidunt. ",
            "Morbi quis nunc sit amet tortor. ",
            "Duis vel purus non magna euismod. ",
            "Sed non augue quis sapien bibendum aliquet. ",
            "In pretium arcu in nunc viverra fringilla ac ac nunc. ",
            "Aenean vitae turpis et felis varius. ",
            "Integer congue leo sed augue suscipit tristique. ",
            "Quisque commodo turpis vitae nisl posuere. ",
            "Fusce in tortor lacinia. ",
            "Nam ac augue sit amet massa finibus iaculis. ",
            "Donec pretium nibh non urna lobortis tincidunt. ",
            "Fusce efficitur sem et vulputate mollis. ",
            "Morbi ut est luctus, elementum ligula. ",
            "Quisque auctor elit vitae blandit dictum. ",
            "Vestibulum sit amet augue. ",
            "Vestibulum maximus erat in risus. ",
            "Ut ac mauris suscipit. ",
            "Sed sit amet leo non metus rhoncus consectetur. ",
            "Nam efficitur ipsum eget augue commodo. ",
            "Aliquam eleifend urna in mauris suscipit. ",
            "Integer dignissim leo ut congue hendrerit. ",
            "Fusce ut elit non metus viverra. ",
            "Aenean quis lorem vel nulla elementum. ",
            "Mauris dignissim nunc in ipsum suscipit gravida. ",
            "Mauris quis ante nec metus maximus. ",
            "Vivamus finibus odio ac enim lobortis tincidunt. ",
            "Phasellus sollicitudin justo eu quam. ",
            "Suspendisse hendrerit massa et elit molestie tempus. ",
            "Duis tempus elit eget nibh finibus. ",
            "Nam et turpis elementum velit ornare. ",
            "Fusce vel eros vestibulum, semper mi ut. ",
            "Donec facilisis leo ut sapien. ",
            "Nunc eu enim in tortor tristique dictum. ",
            "Sed ac ante elementum dui iaculis. ",
            "Etiam sit amet nulla viverra turpis fringilla tristique. ",
            "Suspendisse ut nibh eleifend. ",
            "Phasellus consequat dui vulputate. ",
            "Nam accumsan est facilisis, ullamcorper lectus id, tristique velit. ",
            "Ut ac orci non dolor aliquam tempor. ",
            "Donec scelerisque justo eget urna eleifend finibus. ",
            "Sed ac tellus ac turpis efficitur maximus. ",
            "Morbi ut eros semper, posuere turpis at, elementum ipsum. ",
            "Pellentesque ac lectus eget nunc facilisis pharetra eget id leo. ",
            "Duis sodales leo fringilla tortor aliquam posuere. ",
            "Fusce feugiat velit quis varius mattis. ",
            "Nunc fermentum nisl in risus commodo, vitae pulvinar nibh gravida. ",
            "Proin vel ex a nisl porta cursus. ",
            "Nulla ac orci ac lectus vehicula ultrices vitae sed dolor. ",
            "Sed sodales odio vel ante congue efficitur. ",
            "Etiam a eros vel massa consequat consectetur vitae quis mi. ",
            "Nullam ut enim tristique, fermentum nibh vel, lobortis leo. ",
            "Sed ornare ipsum sed enim posuere sodales quis vel lacus. ",
            "Mauris vestibulum ipsum ut ipsum tincidunt, ac volutpat libero convallis. ",
            "Quisque eu sapien eu leo pellentesque lacinia. ",
            "Morbi commodo lectus vitae velit volutpat, id suscipit est eleifend. ",
            "Proin porta sem id nibh accumsan, vel eleifend mi ornare. ",
            "Ut ullamcorper eros et tellus porta faucibus. ",
            "Phasellus eleifend erat nec lorem tempor volutpat. ",
            "Sed vulputate lacus lacinia odio pharetra feugiat. ",
            "Maecenas vestibulum elit et felis pellentesque suscipit. ",
            "Aenean eget felis vitae magna placerat gravida. ",
            "Vestibulum aliquam lacus sit amet tortor fringilla, vitae dignissim risus aliquam. ",
            "Phasellus hendrerit sapien ut nisi viverra, ac ullamcorper ex volutpat. ",
            "Pellentesque luctus lectus sit amet neque posuere, id interdum massa suscipit. ",
            "Fusce eu sem et dolor fringilla lacinia. ",
            "Praesent sed sem non elit feugiat facilisis molestie vel sapien. ",
            "Aenean bibendum turpis sed nulla tristique, nec congue ipsum convallis. ",
            "Donec vitae odio tempus, dapibus nisl congue, lobortis mauris. ",
            "Nulla ut elit mollis, imperdiet nunc at, accumsan urna. ",
            "Proin tempor ligula nec hendrerit tempor. ",
            "Aliquam volutpat enim eu tincidunt cursus. ",
            "Maecenas sed ex in nulla consectetur euismod. ",
            "Vestibulum quis nisl ac mi ultricies sollicitudin et aliquam dui. ",
            "Cras egestas eros at ex finibus feugiat. ",
            "Morbi eleifend massa id ornare imperdiet. ",
            "Pellentesque suscipit risus vel erat ullamcorper, vitae consequat nibh pharetra. ",
            "Aenean suscipit dui nec tortor tempor finibus. ",
            "Aenean non lorem auctor, commodo justo vel, interdum mauris. ",
            "Duis malesuada lorem et viverra feugiat. ",
            "Fusce gravida augue eget eros tempor tincidunt. ",
            "Sed in libero id dui efficitur mattis vel sit amet est. ",
            "Phasellus placerat nulla nec nisi mollis feugiat. ",
            "Aliquam vulputate enim non lorem lobortis, dignissim fringilla sapien commodo. ",
            "Maecenas lobortis lorem in felis commodo sollicitudin. ",
            "Quisque sed nunc sit amet felis tincidunt eleifend. ",
            "Mauris a magna nec lacus sodales lacinia id nec mauris. ",
            "Cras eu ipsum nec diam sagittis tempor ut vel est. ",
            "Aenean blandit enim sit amet augue hendrerit tempus. ",
            "Duis hendrerit lacus et nisi tempus, vitae accumsan lacus dapibus. ",
            "Donec vitae turpis sit amet tellus eleifend tempor id nec quam. ",
            "Donec eu neque blandit, finibus leo at, porta lorem. ",
            "Suspendisse lacinia tortor ac urna fermentum. ",
            "Aenean a nisi pulvinar turpis tincidunt ullamcorper. ",
            "Maecenas et elit non quam fringilla pretium blandit quis nibh. ",
            "Aenean a magna sed turpis ultricies ullamcorper a non risus. ",
            "Cras dapibus enim quis scelerisque faucibus. ",
            "Fusce aliquet lacus eu libero pellentesque sodales. ",
            "Nulla ut augue rhoncus ipsum tristique congue. ",
            "Suspendisse semper enim vitae dolor interdum. ",
            "Mauris at erat vitae risus placerat auctor sed et erat. ",
            "Maecenas a lectus nec sapien elementum viverra fermentum at nunc. ",
            "Quisque eu velit volutpat, elementum leo a, faucibus ipsum. ",
            "Fusce id eros quis tellus maximus tempus in sit amet nibh. ",
            "Donec blandit enim accumsan, aliquet erat sit amet, sodales metus. ",
            "Donec ac enim at velit dictum ultricies. ",
            "Nunc sit amet augue placerat ante. ",
            "Nulla ac sapien vulputate, hendrerit urna quis, pharetra elit. ",
            "Mauris vel ipsum id justo efficitur suscipit auctor ac dui. ",
            "Aenean sollicitudin risus eget leo pharetra vestibulum. ",
            "Sed luctus massa non nibh vehicula vestibulum. ",
            "Nulla feugiat ante ac orci elementum venenatis. ",
            "In in neque ac nisl gravida auctor. ",
            "Cras rhoncus nunc et mauris rhoncus, a tristique purus gravida. ",
            "Duis sed ipsum vitae sem auctor vulputate. ",
            "Fusce scelerisque sem non enim fringilla vestibulum. ",
            "Curabitur ac est vel risus cursus eleifend. ",
            "Morbi egestas ligula et nisi porttitor placerat. ",
            "Proin lobortis elit ac euismod pulvinar. ",
            "Phasellus eu est sed lectus egestas maximus in ac enim. ",
            "Nulla nec purus luctus, cursus nulla et, auctor nunc. ",
            "Nunc vitae velit eleifend, interdum nisi nec, scelerisque eros. ",
            "Fusce rutrum est ac lectus pretium, nec lobortis leo rhoncus. ",
            "Nam faucibus velit in enim semper egestas. ",
            "Aenean ac nulla quis augue volutpat scelerisque. ",
            "Morbi ac diam aliquet, rutrum urna ac, suscipit nisl. ",
            "Aenean ultrices tellus sed elit posuere, non rutrum nunc condimentum. ",
            "Mauris interdum eros non tortor pellentesque consequat. ",
            "Nulla et elit eu diam lacinia suscipit sit amet nec tellus. ",
            "Quisque aliquam lorem et quam ultricies accumsan. ",
            "Etiam in ligula ut tortor tincidunt rhoncus. ",
            "In eget mi quis purus blandit mattis quis tincidunt purus. ",
            "Maecenas bibendum odio in egestas dignissim. ",
            "Aenean iaculis velit fermentum molestie egestas. ",
            "Maecenas ac orci vitae lorem commodo tincidunt in ac lorem. ",
            "Duis quis dui aliquam, molestie elit non, tempor erat. ",
            "Proin quis neque malesuada, faucibus diam vitae, tempor enim. ",
            "Fusce fringilla velit sit amet bibendum efficitur. ",
            "Donec sollicitudin nisi nec laoreet fringilla. ",
            "Suspendisse molestie massa ac tempus accumsan. ",
            "Donec eu metus sit amet lectus varius accumsan. ",
            "Maecenas vel dui a mauris pretium vehicula. ",
            "Aenean suscipit augue a fermentum gravida. ",
            "Donec pharetra purus in risus vehicula porta. ",
            "Nam vel odio ut sapien sodales pellentesque quis eu metus. ",
            "Suspendisse interdum enim non auctor consectetur. ",
            "Maecenas eget mi quis est eleifend sollicitudin. ",
            "Integer placerat libero non elit rutrum, non auctor massa scelerisque. ",
            "Vivamus ultricies ipsum eu placerat porta. ",
            "Donec ac lectus sit amet massa consectetur tempus. ",
            "Proin lobortis sapien ut semper maximus. ",
            "Sed vitae magna eu mauris venenatis faucibus sit amet a dolor. ",
            "Aliquam et risus at odio ullamcorper egestas. ",
            "Phasellus lobortis nisi a dolor sodales tempus. ",
            "Suspendisse at orci id justo laoreet aliquam ultricies in dui. ",
            "Aliquam ullamcorper eros pretium ex vulputate ullamcorper ut eu turpis. ",
            "Morbi a neque ut justo euismod pretium.")

        var num = (2..5).random()
        var i = 0
        var body = ""
        while (i < num) {
            body += listOfBodyText[(0..listOfBodyText.size-1).random()]
            i++
        }
        return body
    }
}