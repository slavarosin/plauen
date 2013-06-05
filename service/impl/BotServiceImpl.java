package net.gobro.plauen.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.BotService;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SmsService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.service.UserService;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BotServiceImpl implements BotService {
	private static final Logger LOG = LoggerFactory
			.getLogger(BotServiceImpl.class);

	private final static String[] nicknamesEE = new String[] { "VeSta", "Dezi",
			"Nimir-Ra", "Aspidistra", "niut_a", "Dimeroch", "Auriuxs",
			"Маришка_78", "kruopa", "raaama", "Imbocilas", "dani84",
			"mama12345", "kerit", "SAULETARAMUNE", "leopoldas", "lordask8",
			"scorpio2", "ARVY", "karke24", "zozole", "Boginia76", "mciukas",
			"ramune.gelyte", "dafar", "siaulietee", "Xt!n@", "Anupras",
			"paprikaaa", "sandraaaa", "oxys", "Matuxas",
			"Copy_my_diary_anna-anna", "_Монстр_", "I_am_little_misery",
			"kaktys", "Lenalee Lee", "loulou", "Elaide", "Реван", "Vermilion",
			"MercuryMoon", "Мириаль", "Слеза_в_ночи", "Мрадхон", "!Ron*",
			"УтКа - ЕбАнАтКа", "Asaha85", "timmy07", "gavrila", "MaR$",
			"bunny_18", "Defo4ka", "Julenka_24", "Baris.1", "lina06",
			"_Vrednaja_", "AUDRIUS", "Musichka[G]", "Sniezana", "Katyte8",
			"markizushka", "GRETELA", "Anjer", "danix89", "mamina1963",
			"Elvis_Presley", "ja+ty=my", "dainys007", "editukas", "Nataliok",
			"Zingeidi", "Chiko-san", "trader69", "ShiTai", "Милан16",
			"Grimaldi", "Ambra", "kombinashka", "Olialialia", "Сива",
			"Less Dark", "ash", "Spiteful", "Chloe Taele", "Орехыч",
			"Uzuma Sem", "GTR[Sheriff]", "Dimas", "avt", "паганини", "dead",
			"Арамис", "Шизандра", "Abditorio", "Маарет", "Сайго", "Rinna El",
			"bmp", "anemyx", "K@6aJI", "r9act", "ReK", "tsu-chan", "Ribery",
			"AngelsHeart", "Malfoy", "keep_inside", "MorgaN", "Prodi",
			"Мадара Учиха", "Mornis", "Arni 2", "Atrika", "Russia",
			"{T}{R}{E}{Y}", "[DestroyeR]", "Nyota", "Laego", "R@mzes", "cocky",
			"Nastik[aka]LastiI{", "Kassie", "TheSpace", "Гамабунта",
			":-D({})2laR", "Pagonia", "LarkiN", "Auesha", "Enogr", "verona",
			"Duke Nukem!", "Dreamlandy", "Даравэль", "Shikotan", "BapoHka",
			"Miracle", "Lihein", "принц Парадокс", "Rayalitee",
			"Cloudy Dreamer", "caramey", "@Milo@", "Erfes", "Indie", "evekill",
			"Апельсинчик)", "Termit_K", "GrenK-o", "Gattaka", "Tallion",
			"WBYJT", "Sketch", "Rampage", "Синяя Кошка Удачи", "coyot1k",
			"W@rlock", "Aladon", "The_opposite_girl", "Knight_Vladimir",
			"Uchiha Sasuke Avenger", ".опиум", "Атрика", "[JUNK]", "Nero39",
			"GruB", "Quik", "Torionel", "Pifon4eg", "Slim", "Saref",
			".Spinoza", "@zet xXx@", "Brick_top", "SvfloW", "Brick",
			"ЗоМби_КРОШИТ_П@Ц@нов", "(~ГрУSтN@я 3@я~)", "bonus", "motocikl",
			"Daag", "harjok", "3D_LEO", "Delaila", "Cmert", "WinnT", "Vovec",
			"katok", "bondar", "kot77", "Len4ik", "komar", "Tanju_ha", "gbn",
			"sendi", "rusak", "Lisenok", "Nordic_Star6ij", "Vadja", "dedegor",
			"Bestia", "nats", "Lepisto4ek", "Strannik", "Мяфистофель",
			"NadzomiViro", "KiHae", "Yugao", "ИсклЮчение Иz ПравиЛ",
			"MoHcTpицкий", "Lewd", "omg", "Noogzar", "DarkRatchet", "Gelshark",
			"Hemachatus", "ms19", "Cardamon", "VJRS", "franny", "Feri",
			"Causti-k", "Illeana", "n-dorfine", "Enova", "Кей", "Ева Колубер",
			"Лар'аэн", "Бейонд", "yanado", "VolDroN", "Katesina", "Kaiars",
			"Sereka", "headache", "Всего лишь кошка...", "RiT@ChKa", "m4rtiN",
			"Кина-тян", "WIST", "L.Stramonium", "Mira-chan", "ДажеДра",
			"brute", "~[cosplay by rabbit]~", "ZomByaka", "Anriorika",
			"Polike", "Vakich", "evilan", "vgz", "servent", "eolvin",
			"Машкент", "Hisanata", "Lina", "haskykiller", "Ima Juriko",
			"Helsen", "AlicaBlood", "Manwe", "livisa", "Cassiel", "Шики",
			"CheshireCat", "integra-n", "Gokai", "Tiaien", "dixyl", "Kein",
			"Sai Ansata", "Evess", "Everand", "Mistek", "Загадка_Жизни",
			"Маккавиан", "izja", "Сканчита", "P0$T@L", "Flyer", "Сомнамбулист",
			"Chidora", "Domini", "Tornado", "KOCMOHABT", "LIZA", "saVe",
			"sOrMe", "diman aka local", "planoqr", "Marissa_Goth", "DoMuHaToP",
			"Mermaid", "PholaX", "BoNeCa", "Drago", "Jane_An", "Phetahu",
			"WhitERaveN", "Alen", "Arkantos", "misty", "Spirit of the Sun",
			"UltraVIRUS", "fokzott", "Vin†oVkiN", "Слонёнок", "Slonionok",
			"Aelin", "Gendolf", "jdaigp", "White_MC", "Vetalke", "crazy-dog",
			"L00K", "Просто Смерть", "automator", "Honey_Boney", "Z495",
			"kowai", "Нуар Клевэр", "Skata", "Shihodani", "Shnaka", "natamay",
			"Staffa", "Rahnar", "Nanami", "foktozz", "kisa", "Дэйм",
			"Dark[Ol(U23)leneri]", "SlavikUA", "Kaliba", "Dj Nero",
			"The master", "Mellomania", "[ADM]=[FroST]", "Nagual", "в сердце",
			"Saleks", "wolfckraft", "SadHappy", "Founix", "Not Life Silent",
			"Alex_sandra_D", "Thug666", "nafanya", "Тальяно", "Noviembre",
			"yul4ik_princess", "Daymara", "Melodia", "aqo", "betrl8thanever",
			"breakfast3001", "canoz", "daninmanchester", "davbman", "deke",
			"djeavons", "drydo", "edward", "emdek", "great_llama", "hotdog",
			"idontknow", "johnminkjan", "jspano", "lazyjay", "mcgenius",
			"metallisoft", "pmmbala1976", "priore", "pyash", "rjhare", "rock",
			"r.sole", "russell judge", "sandtree", "sergeb", "shandy",
			"spebby", "tom77757", "wonkotsane", "wwjd", "xtab", "xterra",
			"aaRonAR", "addz111", "Adehh", "AirborneDav1d", "Alquinite",
			"anth0414", "AntiTyler", "aooi", "Apocalypse96", "ArNoLDo1010",
			"Atomic10", "Audiochron", "Augusta1", "Baby", "badger0", "Baldy54",
			"Banana322", "blink184", "Caboose1495", "Central", "chazdaguy",
			"Chirpdawg", "chucky110", "CLARKIE85", "coldsr3v3ng3", "coyney01",
			"dadudeondacouch", "Decapitated19", "Deezel", "dermy1110", "dj",
			"Doccy", "donnie", "DoYL", "Dr", "DraedonLive", "dragoonx89",
			"DrkCyd", "Drugsy", "DuffyTime", "dukelivey1", "DUMB", "Dyke",
			"dylbo93", "DylserX", "El", "Elite", "EpicFailMan25",
			"Exitwoundss", "FaTJeRKy", "Fatmaster30", "FlameLordKenji",
			"fourseas", "Frazer", "frontlinechoir", "fUrRy", "gaetanoIShere",
			"gcirrem", "GodfatherGlen", "Goliath", "Grims", "Haikiah", "Hairy",
			"harypmgaga", "homeralone", "I", "ICCPureBS", "ICED", "IceMan",
			"indierokkr", "Jason", "jd1120", "jonofn", "JordPwn", "Jumpify",
			"Junkrunner", "K", "k33gan117", "K3mpy4Life", "kazz397", "Kenyon",
			"Kerosanaker", "knebnn", "l", "Leeroy", "link2312", "Littie",
			"LittleBlueAlien", "LouPek", "Lvivi1985", "m0nkeycheese",
			"mahir69", "Marshal976", "meabaB", "methmanoz", "MilkySheep",
			"Mmmm", "MobilizedFungus", "mordinho", "Mouse", "Mr", "Munkea",
			"MURDADOLL", "mysticdefender", "Nailed", "NetGirlX", "NPT710",
			"OmGiTzRoD", "OverKill1991", "PattyDibs", "PewPewLaz3rZ",
			"PheeelMe", "PiggyNightmare", "Pious", "Pudgy888",
			"punksnotdead22", "RainbowSeven", "RandomRussian", "Rashy",
			"ReVamp", "RockLegend1203", "rOFLyan", "Rollingstone137",
			"RumpaStiltSkin", "Run4cova368", "RUSONATOR", "samillence",
			"samolion", "SevenPairsPinky", "SgtMJRERUN", "Shadow523x",
			"Shadowsphynx", "shifty006", "Silk", "skelerax", "Skiff18",
			"Smagsta", "St0nerJR", "stapletonic12", "Static", "THE",
			"thedan29", "thefenny", "thegam310", "TheOzMozz", "Thraxel",
			"tomvale13", "Trogdath", "ultimat", "viperlord03", "w3v3",
			"WesRulzWorld", "wraithbourne", "xAFRx", "xGoShortyx", "xLAMBIEx",
			"xmatt187x", "xR3GR3TSx", "xWGx", "xX", "xXBundaHXx", "xXN0",
			"XxXBarraCudaXxX", "ZeJesus", "Zombie", "handyScope0", "SADKA@",
			"Velty", "Либерти", "trudogolik", "Mamchik", "Darjana",
			"galinator", "tataxa", "-SUN_GIRL-", "Джоселин", "Primo4ka",
			"ConfliCt", "mortan77", "aljonapim", "lonely100", "Kljopo4ka",
			"Nimue", "Gaskonija", "кусечка", "=_sun_=", "Elea", "Anny88",
			"mademoisell", "radostmoja", "elion", "oxo", "Carioca", "baygot",
			"janes70", "alexksei", "soell", "kvik", "Sirafima", "Асият",
			"RedCat", "Aedverden", "Savinka", "krolltoll", "tanja-tribelgor",
			"Prue91", "viktoria3000", "roberta45", "svetaten", "Её_Высочество",
			"grifon4898", "annuke", "_Stella_", "Valef", "Faruq", "Selekta",
			"DIE", "SunVampire", "Ksandra", "_GiRlKa_", "SveJuna", "Disketka",
			"Stervo4ka_05", "PANTERA13", "ХИЩНАЯ", "veebruar", "airish", "Ли",
			"DIANA25", "kisju", "marjo", "Natalyyy", "MyChem", "кисулик",
			"Briseis", "mannique", "vovka10", "baggi74", "slara",
			"Shantazistka", "Тыца", "zvezdaakula", "Airii", "ldz", "VikuskiN",
			"ksanto", "comafer", "Hammer1989", "mariinka", "TATKA84",
			"svetik9", "lisjonok83", "secret89", "massud", "petrovitsh",
			"svjatka", "даника", "nenormalnaja", "ArekuSanderu", "Natos",
			"Fenichka", "карелина", "kroha2005", "Surikata", "milanik",
			"_Murka_", "karja94", "-_-LoNeLy-_-", "lotus2", "_frida_",
			"plesljud", "OldDenim", "marj76", "-Extrella-", "Nastu6o4ka4",
			"Zmejuka", "viktorxxx", "naine", "Ljudotska", "nastja07",
			"tisha79", "Antmatrix", "solveig0", "assaku", "Akulo4ka",
			"Murka29", "vadiims", "Фабрика", "Nastjushka2", "Paliksena",
			"m1980", "Dimman", "nimfa57", "aikiaiki", "AHAXAXA", "LaFamme",
			"anastasija74", "vhr", "ironkitti666", "sad123", "lBadGirl",
			"E-Sam", "Hytas", "satanangel", "InterA", "nynca", "Djujmovochka",
			"-drugaja007-", "tamar@", "leanka", "maljuda", "reis22", "мил@шк@",
			"SOLOna", "nastee", "Aicha82", "GERCEG", "Jurii", "viktor63",
			"Timosa", "Nadish", "Арианочка", "шлындрик", "gerGIN", "Brjunja",
			"dan1970", "devushka19", "chegevara777", "metacarpalia", "bibunja",
			"Funny-banny", "misju", "Janatska", "Cruel_Devil_666", "ljuba24",
			"Ivanka30", "tribun", "zamio", "ketiv", "pallike", "котёнок12",
			"clover10", "corni", "ChiCa_BoNita", "Graveworm", "HARDWOOL",
			"Tan4o", "viktorsm", "georgjuri", "KyKyCbKa", "k0shka",
			"Kobusenok", "Lamaster", "knjazz", "Lovely_Natashka", "Gedik",
			"Melainen", "basf", "anel73", "eesti_hagijas", "borei",
			"abulafija", "kapernik", "обормот", "ksjuxa", "nadezda02",
			"anna1958", "elfina", "elina777", "sheltok", "malqshka", "inrei",
			"kindral", "veiko", "витту", "Ljenja", "Jarina", "Открывашка",
			"Аутсайдер", "oldUser16412", "ПростоМилая", "Коалочка", "saphira",
			"zakus", "марина2004", "anti100", "Тоска", "naushniki", "kakaska",
			"romsta", "Resident65", "belka1984", "lines", "esab",
			"ssssss-2007", "cxefo", "sanja1972", "papasa", "benja", "MYL",
			"skazo4nitsa", "Weak.", "Whitewater", "galtsonok", "jana28",
			"tolian21", "KodaNIK", "Sphynks", "sudarjuxa", "gexsa",
			"zadolbalo", "boker", "УТАКАЛТИНГ", "asas7575", "LittleSparrow",
			"vitaljun", "Riki95", "KentauRus", "Sisalik", "depech",
			"genius_1422", "Вероника1991", "marcelo", "veta71", "wind2468",
			"HipHopDetKka", "masinkaja", "Adnas", "katjusha", "dark_angel",
			"shecnok", "dimpet", "ТолькоТвоя", "tatiana2", "Snezanna", "KisiK",
			"Natali2007", "Antinato", "Gaskon", "paks", "badgirl666", "Jakir",
			"LadyFlame", "aktive-beby", "groza2006", "petskin", "Julja}{a",
			"Aniksan", "rasta-baby", "svea", "beZ00Mnaja", "AlloRa77",
			"sonikX", "Kitri", "karikakar", "mitta", "Brjakabum", "J_u_s_t_Me",
			"djuba", "corruption", "mama0707", "LitPrincess", "Артышка",
			"jerjomga", "Фэнтази", "^dramgirl^", "veeera", "peep", "malys25",
			"от_греха_налево", "masik_kiss", "Marisabell", "lenkuz", "ALisEK",
			"Mishanya78-78", "Anastasija777", "aquarelle", "faik", "justja",
			"Fr1end", "viktor626", "Crazylka", "Lorena_In_Love", "irin4ik112",
			"verulja", "Andaril", "Pelog", "Mitri", "viksi89", "spais60",
			"ilja1995", "shatshkov", "SветЛанка", "olka52", "lora41",
			"-angel15-", "aien", "voly", "Rakan", "бабуля", "SonZZZe",
			"minotavr", "ЗинаИзЛимузина", "Lady_Dark", "Liliana20", "артик95",
			"someting_nice", "fiona8007", "veselaja", "gusset", "KYKOJIKA",
			"InGodsHands", "LORENA2005", "hrjusik-smusik", "Elizabeth",
			"dardevil", "semeikaadamsov", "korni96", "kwint1", "Timur2",
			"sijanie", "ImmortalFilth", "ТемныйВолк", "Чилика", "ViiKa",
			"Ko7enok", "street7", "Maksik2006", "am123", "Mur04ka", "DnB_girl",
			"elmix", "Дарьяна_", "ALYCE", "A.KOMSHIN", "larisa1", "Tous",
			"Пёсик", "galok", "Bratishka11", "Chipe", "svetlja4ok27",
			"ljudrang", "Djusha", "Екатерина86", "SvetaSuper", "koka106",
			"juto4ka", "zai4onok", "alirs", "hoppa21", "КаТэ", "ptcela",
			"tatoo90", "GhettoJane", "Брюня", "corny", "No_amo", "gazel",
			"GIpeRBorEI", "---jevgenija-i-", "D@rJa", "LinkyQ", "anel_",
			"estec", "пистолетТТ", "kalju", "reper", "dimitrius1980",
			"moonbotica", "demonik-78", "HaCeHbKa", "zvezdo4ka", "RatLio",
			"HappyCat", "Son-Goku", "soldattt", "Lonely_soul", "jeleenka",
			"Джелит", "antila", "ciccu", "Лёля_", "Lindsey", "BalticGirl",
			"Kiska115", "Lasjoseph", "Svetik33", "Lucy-Nyu", "Krashenaja",
			"Liidia", "Laro4ka58", "pinni", "midjai", "war_sir", "mimoza90",
			"Jelizaveta2008", "Niinulja", "ogurez", "Candy_Girl26",
			"Lenocka87", "Ljubanike", "dev73", "Axara", "Simanena",
			"Ritatulka", "ruotsian", "littlestar", "Джевана", "dzina", "RQBAK",
			"Triller", "Ljolka08", "Гермиона", "Raudzig", "StReLeC2006",
			"vetasja", "kriska-lariska", "jana83", "dmitri26", "Saviinka",
			"Volodja59", "olgalysp", "NarvaDrap", "Mirosjka", "The_nice_girl",
			"·•_Lino4ka_•·", "daitedeneg", "венецыя", "Lillа_", "Alehan",
			"djavolica666", "Alex_Estland", "liis02", "cleo15", "Inaccesible",
			"~_V.I.P_~", "eve-li", "TOLYANO", "Likos", "floor", "Дианочка",
			"Ма_донна", "sico2002", "-berra-", "maxzim", "Dasha-angel",
			"romansson", "sanchez-1985", "jelenai", "soulsister", "vindicta",
			"iraton", "vikaviktoria", "ghost_dog", "Dakota353", "Чeната",
			"IBuIKyIIIa", "koljamba112", "P.K.(Екатерина)", "diiil",
			"plazma79", "yellowleo", "kroska25", "MASTUR16" };

	private final static String[] nicknamesLT = new String[] {
			"Cloud Guardian", "Sabas", "Texx", "Wormrider", "tanto", "olha",
			"sauluze", "wladek", "dee-mone", "ZvEzD@62", "Primovera",
			"Ирисовая", "Comotoza", "Сладкий_ноябрь", "LALAILA", "Kseni",
			"vilniuk", "angelsmoon", "Анатолия", "Montesuma", "gazolin",
			"Kaprizulia", "y0zhk", "mazoji", "Liolika21", "Marytė", "Ne6aTiVe",
			"lana4ka", "A-PAU.", "Robikas", "хаку", "pandora62", "Птичка",
			"vaikinukas", "dinoz", "pakalnute", "Апостал-13", "СиГ",
			"nastia123", "Augiux", "Andrej_L", "gabriele9", "devonka50",
			"BIRDFLY", "Valant", "elva90", "vp1233", "kanapinis", "Воскресшая",
			"statik68", "-MF-LT-", "olialia7", "lovely_bunny", "ласандра",
			"Arzuolas", "micail", "Aronas", "Moncia", "bravo24", "boruzele",
			"АНГЕЛОЧЕК777", "DeadMorozz", "Lesiona", "Clandestinus", "Жжужжа",
			"malenkijdaun", "Damijona", "da6ule4ka", "dalajla", "Сивочка",
			"lorriukas", "Natali1039", "shponkytia", "VreDnuLkA", "juraciuke",
			"Kuten'ka", "cobra-beer", "trikoro4kin", "Nellyja", "Rose_of_sea",
			"Murionok", "Natufka", "perednyte", "Есаул48", "dj_aleks",
			"emusia", "syns", "nEpOsLuShNaJa", "karincik90", "smers",
			"Weblamer", "Nagigor", "Aredar", "LCF", "Essentia", "Prosektor",
			"freedom", "nondefault", "кареглазая мечта.", "SapphireNymph",
			"SILA174", "White Thunder", "Christall", "Sov1etnick",
			"Britvochka", "An Overflowing Idea", "Zikferd M.", "Jamato Mitshu",
			"Lunnaida", "~Tori", "Любовь Стоит Смерти",
			"fA | K.Roman ~ rOmjkeee", "sleit", "Ирреальное нечто", "jek@",
			"EXP", "twelpy", "Kapcap", "Prince de Cristal", "TankJR",
			"Jenny Li", "Veniel", "Rent", "Рыж", "STASON", "7,62х39",
			"Grafinn", "Temmy", "4y4a", "rube", "solnusko", "vaidaaa", "Twixs",
			"fiodora1", "Лепаите-Ева", "vika-boom", "-=+Братва+=-",
			"Lusejeana", "Varliukea", "serdz24", "aliusha", "Miraculum",
			"[Muthafucka]", "OlgaAB", "Annush9", "dmitrij0860", "Black_mind",
			"skorpiosa", "NikolajLit", "vidule", "Stasys_N", "broske",
			"AceAcidHead", "pitonas", "rock94", "Agawa", "Dilai", "охот_НИК",
			"mentirosa", "Дурадурой", "tigra2008", "Ta_is", "LiKTransa",
			"DiabolicA", "virtualus", "ABAPu9I", "Kristinute", "Hasco",
			"Африка", "Daile", "grusiukas", "DJ_MAXIMUS", "erotic007",
			"collina", "Rizha", "Jenifer22", "solvita196", "(c)Хост-Ведом",
			"Agnute_angel", "Axina", "ЭЙ!", "Girlo4ka", "emzas", "мирный000",
			"глухарь", "JI9IJIbka", "НеЛеГаЛьНаЯ", "andriusnk",
			"веселый-парень", "сарочка3", "viole_ta", "nipek", "modikas",
			"baniuxe", "Angeliukas", "Ghost35", "aistera", "Aleksa_", "Peleda",
			"BuTaJIb", "olesiuska", "kam4ih", "glarus", "UltraVIOLET",
			"ira560722", "Valerun", "Alisi_ja", "тут", ".Caroline.13",
			"natallyyy", "SavinaMila", "natta851", "Agnija83", "Proca",
			"lenocka66", "Nazzgool", "mazhyte", "drivemecrazy", "saule-emo",
			"heaven_angel", "Irma_irmute", "DARIUS28", "ispanas",
			"SmExAtUnCHik:)", "Loreata", "marKel@n", "Melaschenko", "frejeris",
			"norafa", "BlueStar", "siaulelie", "panteriuxxe", "Indenas",
			"Daisy^chka", "Modesta", "mustangas11", "kalambija", "валёрик",
			"Diancik", "kisiunia", "MavriK", "Popo4ka", "Sirdukas20",
			"KpacoBKa", "<marina>", "JusTcrazY", "AHTuK", "loricka",
			"BlackWolfy", "_Ryto_rasa_", "zahira", "aleksmax", "fortucia",
			"MustangLT", "trolyno", "rituliukas11", "mokslokampelis",
			"sooskytea", "IngridukOu", "Kristijonas", "tixij", "RAMOVE",
			"koma-talib", "AnGeL_Of_DrEaMs", "KoMPIK...", "badiaga",
			"No_Nicky", "мечтаа", "упырь-2", "malij", "sumawedwaja", "acteeno",
			"Лисандра", "KristinaDrosd", "divaina", "jewg", "luvluvluv",
			"Liolia18", "sergej64", "Innulkin", "закавыка", "Tigriukas",
			"Sapphirra", "bronzine", "Kарантинка", "Милый-Тигрёнок",
			"mila4ka69", "troe2", "feni4ka", "Tomcat-f14", "seras1971",
			"Neizvestnotj", "Сранствующая", "MPA3b11", "li33na", "vytautasm",
			"klau", "sypsenele", "ramunelle", "tomka1998", "Paprasta",
			"agnyte_forever", "ssauliusss", "regis", "Асенька", "Lesch",
			"lenochka2005", "СергейКа", "Свася", "elf1k", "KaprizuIia",
			"Dagris", "piktutis1", "I{atrin@", "tomejus", "Erinnija", "Miiila",
			"beata0103", "crystalla", "Katuuukas", "lellyte84", "wezzza",
			"kastonke", "katyte241", "Sinbbadas", "zbiga", "Gint",
			"atombombwarrior", "gedaskl", "Deemer", "Dajms", "krolikas",
			"Dziovani", "butia", "ZajeC", "Франук", "arinuwka", "Janeta1983",
			"K12", "oksan4ikas", "natalija@nekto.", "evlampija", "Litlis",
			"N_A_R_I_N_A", "albinas33", "Ivka-Sivka", "FIREfog", "ksiusa",
			"augintine", "Proletarij", "svececka", "Filomena", "Sherali100",
			"dancing_tears", "Meskinija", "Aura89", "sunnie_sky", "FoRCe",
			"eMapco", "1vadim...", "smalok", "killerrrr", "deiamntukss",
			"Ivonacka", "Ucenica", "SeSaM", "Disan", "aleksstep",
			"jedi121master", "Fiole", "Jasnoi", "maxtop", "karakisska",
			"v1rus65", "lenopsik", "РУССКИЙ-дима", "olega.vlasova",
			"camgirlfun314", "djavol", "Шпрот", "defender", "svaboda",
			"Tanjusa", "pedalkin1", "Wkolnica", "millagra", "bora_bora",
			"sa6a_in_dreams", "бэн-тэн", "Reginiux", "^nuneTkA^", "zajca",
			"neonke", "InnocentAngel", "Chikis", "Oksis", "Vlasyk", "B0NUS",
			"Vidas", "N@@ti", "Ky3bkuH", "kanape-e", "BARAS", "nataliuke",
			"pica", "dimas2000", "Olezha", "kostilio.lt", "AHTOH11111",
			"TamerlanKa", "Oleg73_33", "DiAnACka", "npuBuBKA", "Ivonchik",
			"superdetka23", "Ketrin(n)", "R@munele", "Laiminga", "kixa30",
			"darek", "shvedas", "nikki1", "Seks_partnersa", "DыыынЯ", "Miafka",
			"Edvard777", "purpurlyt", "Darcness", "kolobook", "Ed'ka",
			"NASTURNA", "Glebas", "panda311996", "zuikaaa", "ja22",
			"runescape", "GerojiFeja", "mixalena", "Jheven", "Kipsiukas",
			"Black-Sparkle", "AnChovy", "V.V.Putin", "NEZNAKOMKA67",
			"lena2000", "doncikassss", "biliardas", "ferum", "lostinspace",
			"Xopowuu", "wench", "Lelyte", "escarabajo", "espresso", "sergbajk",
			"divaina40", "Liutik_777", "likulka", "MeJIku", "SANLIT",
			"Kallypso", "Minne", "MAX19870708", "Gabrielyyyte", "Saniook",
			"evgtn", "wacek", "lamantin", "shexpyras", "Margo6a", "pazystama",
			"xalera36", "HopStop", "crazychizh", "nikish", "Belora",
			"FoxOilers", "Prada_", "cardik", "Изотерик", "vladukas", "sotus",
			"Nattii", "frequenr", "apocalipto", "lentik", "harlow",
			"Peledushka", "Do_Fight", "justvitiok", "feja669", "foxterf",
			"Leila1", "Ziablik", "long2", "Sajori", "ms.ganja", "98", "Drinni",
			"Viltis", "Dagna", "rycyk", "Tad.1", "boorbuls", "eLViDi",
			"АNгелоk=)", "88akmene", "", "leonid2000", "+CRAY+", "karpis11",
			"Marina36", "olgaa23", "Нетудоманикого", "Milaska_18",
			"dimon.03.12.63", "kristulyka", "DELFINAI", "Audrius180",
			"Long_Oil_Venom", "Dimantas", "DFJI", "Sliven_LT", "legro",
			"ofters", "astoOotea", "nora12", "Matujza", "sakurax", "Vorozeja",
			"lormar", "Gliu4nica", "Zivusaja_mectoj", "emostarlight",
			"Sakalas", "valerijLT", "BRIDZIT", "Mellusina", "Masquerate",
			"KARMELITA22", "kakavi", "ddinis", "VovaV8", "CRAYfish2",
			"serov94", "kaunute", "baxuriukaz", "Sergejus", "kayrkayr", "semo",
			"rugiush", "pasha2b3", "AlinaMly", "vyras", "Zen4ik", "ЧудАЙА",
			"sinichka007", "KoMPIK...", "Alina_vln", "dainoriukas",
			"neimante1", "pastas", "linas999", "rejnase", "kisulaLWC",
			"WiTuSiK", "мечтаа", "SweetChineMusic", "nasty_slut",
			"Aburcesoubs", "vvuka", "neklauzada4", "kirgizas", "akiplesa",
			"jevgenij", "algis45", "Prity", "DulceMaria", "EGLYTE", "Nosyte",
			"dian2002", "fiesta2", "Alicija77", "Kiesaaa", "sirse84",
			"Entropija", "pukute28", "Coongenserolo", "igodom", "Lauryt",
			"kleckaaas", "kapiuson", "Lapasss", "Ks6666", "lt-sima",
			"domovoin", "kaktusas>", "simuciukas", "dimon3191", "NokiaXXX",
			"kibinuke", "ultra2001", "93", "lolke", "cernuxa", "violan",
			"alfija1", "kalioshis", "pobeda61", "julytia", "ilgesiuke",
			"Karolle", "Mellomane", "Лисандра", "blobrer", "dewu", "pushke2",
			"enumar", "juodva", "artis_63", "NGC", "Nemunas", "suniuke12",
			"-=AlChaS=-", "jovitea", "baltakase", "mocia", "ausra114",
			"andze2", "13monika", "88.222.220.0", "d3v", "irmuteh", "Snuffley",
			"dovi", "tania41", "gretabark", "nerka8761", "gabriele98",
			"zalonskaite", "darkaairis", "_Modena_", "karole123", "Kasia25",
			"panteriux", "vbudrik", "Durex52", "ernestikee", "lialia_94",
			"Pastrigus", "Innulkin", "sinchuan", "gele23", "...EvUsIa...",
			"hera", "Natuskin", "Deividaxxxsss", "PAVLOSSTER", "dimy55",
			"dzonuxxxxxx", "mullatuke", "sackura", "Safe", "dynka", "tinge",
			"ppapas", "модуль", "Sapphirra", "mmulkee", "sigusiukas",
			"1122211", "ElivoD", "emo643", "atera", "karmelava", "astuted",
			"budrik", "zaihga", "mmmmmmmm", "colliuke", "matijokas", "sonmar",
			"eleonora123", "Vanessiuke", "wetus", "olialia", "biakabiaka",
			"jurga-jurgita", "torkaj", "Гретка", "Ashur22", "miramixxx",
			"Ingute", "закавыка", "SinDiAngel", "MARIJASIAULIAI", "kisiaa",
			"tomazo", "Evelinaaa", "Kristinacka", "slavka1", "p0Rke", "pasala",
			"kazkasbetkas", "liguska", "Aniuto4ka", "nerkax", "Fiola",
			"kalamburus", "akeles", "jukgin", "kvaksiukee", "laimarus",
			"sherringan", "zuzirecord", "linelis", "aleksandras", "Bulltike",
			"ledynas", "Hauzeris", "roka", "MESKA", "KHz", "ziobrik",
			"riccius", "упырь-2", "linucka", "gintaryte", "Julashka20",
			"ingulka", "skinignas", "Tanyte18", "tania811214", "princesssa",
			"likas1977", "delfin111", "Clod2008", "Alinakgc", "rojus", "ardnI",
			"indrenau", "Чертя", "Inf3ct[e]d", "Ankeris", "ausriukas",
			"Braske32111", "Smaild", "vitenas", "Ramunyte", "ugnYTE", "Livija",
			"xqxqxq", "lydyte", "margre", "luka2007", "KISA007", "nemoksa666",
			"tomux18", "divaina", "xrenovina", "vorvik", "RolandZark",
			"alisa484", "jenja626", "as_hai_lin", "laddy88", "Jolita",
			"poveris", "eziukasmigloje", "Nataliok-e", "antaviliene", "eima",
			"Justea;]", "-[SABA_GOTH]-", "Piotr", "daryyyzas", "vinipushik",
			"kristutedan", "mds321", "svituris", "valport", "voromiksas",
			"nersima", "Windigo", "Misterious", "cccccccccccccc1", "seyraster",
			"kaljuga", "Ezas", "maxasJMX", "luvluvluv", "doogg", "merlina",
			"Taxom", "world_of_ice", "Ritake", "eva30", "ruonis", "tadas112",
			"myliukas", "moniikaa", "ktoTY", "maziutuke", "xxmesxx", "loimera",
			"KristinaDrosd", "vejunestela", "olickaa", "Egluze195", "autorun",
			"saniok123", "Dija23-69", "papunis78", "jelis", "emazoji",
			"digdogg", "mode147", "canrulz", "Sasori-dana", "mixern",
			"bambukas", "Lneringa", "edgar28", "nICK88", "g.plunge", "lazunas",
			"Fyfute", "storm_", "tatka_", "Anettta", "sonnica", "mikutiene.l",
			"buziukas", "zaza55", "tawilik", "justyna222", "Kofejinas",
			"lelija", "kill_kitty", "Romas75", "karlaa", "stabas", "mantyno",
			"jjuoda", "Fanasss", "bimkadum", "kraf", "X_Mas", "nicknfshfdg",
			"kurbe", "laima11", "daivute111", "velniuke", "miauksis",
			"Renapupik", "airis09", "tamosiunas", "HeyLaura", "Graccy",
			"Stelliushka", "witkute", "ginciuke123", "drigne", "Vasilina-т",
			"Dimchas", "-=dj=xoops=-", "gincia", "fatinija", "Tigriukas",
			"Zelenoglazaia", "MERKINE", "vitaljano", "BartSimpson", "kreta",
			"azara", "пит", "akirew", "pikaciu", "Tarantinas", "prixotka",
			"SanyaON", "Tautytea", "darija-daiva", "далвос", "d_e_v",
			"byziniatka", "solnisko_2000", "livu", "juratyte", "lialiuk",
			"mcjurik1", "zaliom-akytem", "ieva15", "kuska-16", "gruzas",
			"vzydrune", "nsxxx", "galina35", "ledine", "Swan4ik", "Ahabibi",
			"tona2006", "kestas44", "Bapsilealay", "junrob", "vikkute",
			"Emilyte", "Anarhist19", "angelus_87", "ragaba", "connectas",
			"Nitnuantantah", "egluciux", "gelyte", "yhx", "grandddd", "veite",
			"bobteilas", "kalija", "prostaazazel", "artur333", "jewg", "vikte",
			"XorosijChelovek", "alweaxD", "brigut", "Sonya666", "tfint",
			"mazgele", "_LIPKAJA_", "geparde", "blondineelee", "Nenis",
			"valiunia", "arvydas34", "cenga", "MamaLena", "Debeselis",
			"Vai2lia_23", "Mirusia", "malij", "iri7", "virgisvkk", "styga",
			"w-idmas", "mattisas", "natass", "amooks", "badas0", "Mikepapa",
			"dimaarchipov", "baltusiote", "emutea18", "acteeno", "sumawedwaja",
			"IIBA", "stela-gabijola", "rimaska", "kristulele", "bailamor",
			"mirek777", "just.tanja", "bronzine", "proofox", "alfrida",
			"esmute", "Liolia18", "niuta", "Walodia", "Barbeq", "sirduzeee",
			"mashka-kakashka", "indrucka2220", "ana2", "garfildazz", "Liebe",
			"Akage (a.k.a. Hi no tori)", "itaurus", "Hellen", "Роял-Драйв",
			"Veta-konfeta", "Ostap_Bender", "~Луна Лавгуд~", "Spoller",
			"Sataniolla_Milton", "Forlink", "Skvoal", "CrazyFeSS", "Elio",
			"Alik", "Niari", "Iriya", "ArchiFuck", "near", "марусишка",
			"Yana-chan", "Reiko", "Джордан", "Фсяка", "noyrin", "Ranzo",
			"HeleM", "Аллен~", "DriverSTI", "ChuckNorris", "Бендер@94", "AREY",
			"Aveiro", "W1zzarD", "Redox", "Май", "Torica", "Мистейк", "Elgir",
			"Himmelske_Engelen", "La mignonne", "Fuzz", "Zillah a.k.a. Ziggy",
			"oracle...", "Джон Малевич", "zy", "DOdGEE", "Anetina", "gen",
			"Lawliet", "meSh", "astenfost", "mariesolo", "Legolas", "TRENT",
			"Пугало", "Адриана.", "ICE", "Янаги", "Bafin+",
			"Shinmaya aka Fred", "Akini", "Кофий", "Silverymouse", "Vladanna",
			"Riddle Skif", "Moonny", "тру_Герм", "Jliay", "IIAJIbI4", "Alex",
			"Nerieru", "FeRS0", "Элха", "ailexelia", "aleksa_sk", "Lekshy",
			"Khanyl", "Абигейл", "Psycho", "vzmisha4", "solitaryspot",
			"RuRik fish", "[Orochim@ru]", "Babygirl", "Symmachus",
			"Кира это я", "Dim0K", "Thindomedel", "Kelting", "Ник", "Eliora",
			"Виии*", "LanSeer", "L!stopada", "Entey", "POZDI21",
			"BlueButterfly", "ЛАЙТик", "Ятсан", "m_dyke", "~+Retro_Girl+~",
			"Bars", "Al Bandy", "Мика", "Aldegid", "Hamster", "Elrohir",
			"Shad90", "Mitsun", "mysteria", "†Doberm@n†", "3skaska",
			"Smertyuk", "Ленин", "Shagel", "franz", "Velset", "asgeth",
			"acckiy", "Actani", "Rosielle Kadavercian", "Chrno Asakura",
			"Нему-идиотка", "Estella", "heroine on heroine", "Mess J",
			"/7cu}{", "Kordy Black", "rejw", "Cenod", "Alven", "fibi", "Взмах",
			"oooiio", "Caramell_Blood", "Todeskampf", "мелкий Takeo", "Hiko",
			"EUGENE", "FlyDIMMear", "Listen Liar", "Madbutcher", "S[T]EL*S*" };

	private final static String[] nicknamesLV = new String[] { "Popandos",
			"Coroner", "Jonas", "EneRGizeR", "STN", "Igo", "lino", "Kot",
			"Ljasja", "ino", "viki", "fedot", "Amsterdam_", "eli4_ka", "laska",
			"pusikat", "mamulcka", "Lambada", "prive_tik", "jefimka",
			"majuwka", "rose", "Lapo4ka", "micra", "Bichasniguz", "bossingham",
			"spaceman", "DC321", "venia", "Basilio", "Tofka", "Allen",
			"Diamond", "Revenant", "Revenant13", "Dimon", "spok", "mihalich",
			"Yahoo", "Ne_promokla", "Rolik", "BuGSy", "kAnfetka", "maliw",
			"WEFINJA", "Skor", "ANDROID40", "bestaj", "Goshik", "Amigo",
			"radisto4ka", "infected", "Devconka", "extrem", "Samanta", "Vadik",
			"Saitan", "tsdemmo", "PinkPanther", "xomjachok", "viola", "BiT",
			"amalija", "BOBIK", "santik", "OTMOPO3OK", "Skarlet", "Skarlett",
			"Ritkin", "LioXa", "LenKa", "Lena_solnce", "Neistrebimaja",
			"VOLJSKAJA", "_hawk_", "lja", "_kisa_", "ste", "sant", "Kristi",
			"Pharaoh", "_morkovka_", "EDVANTA", "sam_dobrota", "Aldaris",
			"shmuce", "silvija", "Papochka", "_MIG_", "Anathema", "Vitalka",
			"Dispy", "ULibo4ka", "bull", "tehnik", "Gopa", "katja",
			"Sasodiits", "kalipso", "bestja", "kaketka", "koketka", "sirdnaX",
			"Monastyr", "ace83", "Bomber", "LubitelPiva", "Chupa",
			"Poterjannaja", "manki", "Dzherik", "varenje", "Lt.Degraf",
			"SiLvErMaN", "Fantomaz", "grizli", "Eliezer", "Spartakich",
			"Sajmon", "ONA", "Gucul", "Interfrend", "HoT-GirL", "Paci",
			"klexis", "Laurita", "Pocahontas", "pesnja_N92", "tank32", "Zelma",
			"xas", "Plastic_Angel", "Elfijka", "Galchonok", "AS1984",
			"covergirl", "Lastochka", "Diora", "multik", "vanca", "aleksas",
			"pacz", "snaiperlv", "zubr", "model_v_dzinsax", "Valentinka",
			"borman", "Simona19", "BadBoy", "einsteins", "Edgar", "mazafaka",
			"francaise", "vadoss", "MIKAZZA", "Adolf_H", "_koshe4ka_",
			"WeArezzz", "insatiable4", "bolt", "model_v_jubke", "vreda",
			"BOJARIN", "neznakomka", "bill", "hwaran", "blade", "blejd", "boy",
			"Mudak", "Edja", "dd7", "marija50", "Glad", "sten", "Ljuka",
			"rasssta", "iriska", "Kretinka", "amerikanec", "AEV", "selena",
			"nevesta", "Panda", "Vetrjanij", "Viagra", "Aneta", "tanjuxa999",
			"Christina", "gothic_vampire", "Bandit", "VADIM", "olegush",
			"Chita", "brjus", "drjupa", "kovboj", "dinuljka", "Jason_X",
			"Iri6ka", "_BadBoy_", "ViruKs", "murik", "zemlanin", "shurik",
			"devilgirl", "Wild_Wind", "bomch", "murzik", "KOTENOCEK",
			"Stervo4ka", "_Ta6a_", "Cordelija", "98b7", "Aisha", "Zebra",
			"gafurov", "Evelina", "treker", "ANDRON", "rasta", "IgarexA",
			"Professor", "Daugavpils", "SaNeC", "happy", "Alexa", "nika",
			"Dzidzju", "zainjka22", "IIkstiite", "SLADKIJ", "Andrjuxa",
			"edgar007", "tanjuska", "Denisxxx", "Creeping_death", "oxy",
			"nastygirl", "mishail", "ashtray", "_PANTERA_", "_STIHIJA_",
			"Kaban", "marior", "Ega", "nikolaj_ko", "Anja", "krisenok",
			"Lenych", "Annushka", "bummer", "bumer", "malaja", "grusha",
			"raduga", "RET", "fkate", "PrZerg", "Lex", "aal", "ljusja",
			"Marlboro", "sky_lord", "Sladenkaja", "senja", "Fongitar",
			"Serega", "Suslik", "Dimmu_Borgir", "Maliwka", "Zanda", "ondrij",
			"Busjka", "Bob", "busjona", "rozi", "Vadim2", "Pusok", "Reunited",
			"turist", "Lenok_26", "Butilka", "LiLi", "Ksjuxa", "Inga", "turbo",
			"Ifrit", "dik555", "Mywki", "Snack", "mono", "Agent_Rat",
			"Bene4ka", "Daddy", "Ne_Kurju", "Legion", "amina", "mihuil", "www",
			"Meitene", "Yagodka", "GRIDAS", "BORODA", "LILITKA", "Nika_a",
			"TheaTral", "BeastMaster", "inta", "Kowka", "Wild_Jackal",
			"Goshik2", "Gor29", "horosaja", "missy16", "Frog", "Miracle",
			"Francuzzz", "Krasivaja", "malishh", "Dejf", "dad", "Tibor",
			"Andjik", "veter22", "nezno_v_gubki", "Degraf", "eridan", "allena",
			"brodjaga", "rider", "WINNI", "dixotomija", "borkat", "Angely",
			"morozzz", "mika", "MISHU", "djavol4ica", "naglijs", "Bob.1",
			"LEOnardo", "renatik", "Putin", "Nord", "au", "Ljuska", "MONOS",
			"Die_Maugli", "sasa", "Cmerts", "Dzek", "rassta", "vanca_",
			"_ArCheR_", "Ele4ka", "Julinjka", "lamerito", "crazy_girl", "SiDi",
			"svt", "adas", "Angry_girl", "Vorjuga", "Koroleva_snov", "4uvak_7",
			"prikolnaja", "via_gra", "Belij", "JCB", "Egoist", "Milawki",
			"ludo4ka", "FallenAngel", "Boroda.lv", "_LjoHa_", "__belle__",
			"ZV", "Speedy", "Krista", "Prince", "_Pastir_", "Ljelja",
			"XaTaBiK", "borrman", "Zjuu", "muchitelj", "Spaun", "SABUTILNIK",
			"_SiDi_", "Acidity", "_ega_", "djimi", "man18", "yulia", "Das",
			"Linka", "cwc", "Daxa", "Apxun", "Yuki", "Stesnitelnaja",
			"Ljaljka", "IgoRjoK", "Fishman", "Lerik", "PAGANINI", "Pupkin",
			"Dimitrij", "Rastomanka", "DeNiKK", "Igorj", "Eveline", "_JaX_",
			"Arishka", "dimich", "ko6e4ka_16", "KiSa", "Kisa_Def", "Ananas",
			"acid_disk_jokey", "Es", "livle-girl", "belosneska", "fedya",
			"belasne6ka", "teri-belosneska", "__zaloznica__", "Neznost",
			"krasotka", "zaloznica7", "kosecka17", "FraDee", "juljecka",
			"varlden", "AnXeLiKa", "5230953", "Laiminja", "Laima", "Login",
			"1", "kowkaN1", "Voland", "baby", "ai6ka", "ERETIK", "Migera",
			"Pirate", "kOKS", "_Shnjaga_", "_SHNJAGA_SUKA_O4KO_PITUX",
			"mc_vadim", "Bumers", "A_D_J", "tina", "Linda", "Milady",
			"6kolnica", "Dreambaby", "_VItaljA_", "samdobrota", "sam__dobrota",
			"Lena", "Dolbojob", "Vitalik", "inoplanetianin", "Silence",
			"_Versa4i_", "10226", "pf4eva", "_Eretik_", "MaLisKa",
			"_BIZKITgirl_", "Zaja", "_Nastjuxa_", "_MarkizA_", "simona9",
			"olesja26", "tanko", "Kaktus007", "AnastazisWhite", "kolobor",
			"kristilana", "martusja", "UniStudio", "ELIZA_BET", "Картинка77",
			"burbyaklis", "Artua07", "=УЛЫБК@=", "мармозетка", "Svetik_",
			"Diza", "Marihuannka", "Juljawka29", "СВЕТЛ@ШК@", "shmyger",
			"Mila12", "ЗАРЯНА", "Skorpion53", "SOLNU6KINA", "zvirbuli",
			"yura_n", "zvjezdocka", "laly-alexandra", "sataniel",
			"viktorijavika", "Кристин@", "Троу", "darkmera", "tihonja64",
			"СветаВихман", "_Ната_Ли_", "edneti", "_|ET-", "челсик",
			"katjuxa15", "primula", "RONIS", "sweet_kiss", "Mil@n4ik=)",
			"Iriwka1987", "Julj4onok", "SVE4E4KA", "Hameleoncik", "Tatjanna",
			"derskajadewo4ka", "Машка", "НАДИНОЧКА", "Ko666e4ka", "затворница",
			"lamira", "DEVO4KAgej", "FVA", "Hvorobij", "Teka", "malish7772006",
			"zaicik16", "ulrika", "Барщуня", "Валфед", "Andrejj.", "Valmin",
			"Dragona", "Невесомость", "leriss", "Dusja1005", "globus21",
			"mariisha", "vera79", "Alenka12345", "Ljvica", "Murjona",
			"ATMOSFERIX", "Молоконасос", "Igoner", "Chiara-Moon", "Aljo",
			"estik1", "_Toffy_", "ENNY", "kasak", "LaBzDuX", "ЛетучаяМышкА",
			"Сайрол", "rizenjkaja", "galo4ka-40", "andik126", "ka_tja",
			"estik", "same", "Stephanie", "aleksa_c", "igoreha88", "savskaja",
			"Anonimka89", "YourGodness", "ladymoon", "Jurrrban", "Чучумуйжа",
			"QUIN69", "Busenka89", "ЛюЛиКкК", "sabinalena", "BeyounceLV",
			"x-gamer", "Iveta", "Kroha_ju", "Happy_Tinka", "vara51", "lino4ka",
			"studentka1969", "vor@tok", "Nadina0701", "VOVAN89", "lostrain",
			"Просто_светлая", "Ушки", "Ardisia", "elena0508", "selentium.6",
			"Mumije", "Angelic_Scars", "Инфузория_Тапка", "katjuxin16",
			"benitos", "Delfinochka", "MaTuAc", "дикая_скво", "sennj", "ak53",
			"BEPA-ЛУНА", "KOROLEVAxxx", "Flawless", "jurec8", "таисов",
			"lenor65", "karo4", "ogorodnica", "парадокс", "irina-rudzite",
			"Vertixvostka", "Magdalena_M", "Елка_Обещалкина", "Игрулька",
			"Daugavpilchanin", "kasjamba", "Kostet89", "vikulja22",
			"777Irisha777", "stervachka", "юрец29", "LLIaMaH", "Лайма0410",
			"KpucTu", "Leno-4kaa", "ANIKMA", "demondark", "HStudio",
			"breimanis", "PrikalisT", "merehljundik", "AmSi", "sandro18",
			"Unlimited", "sensuuum", "ilonik69", "izeja", "некогда", "LBK",
			"Ivan321", "^Мариньчик^", "cheeters", "Teg-Stra", "SweeTy__Girl",
			"nastjonok", "прыжок", "Saulite999", "Haxodka", "200", "Putiko",
			"virus25", "harita12", "zelanov", "natusja26", "конспиратор",
			"777Vasek777", "Cantona", "korvin31", "MARIME", "Neljka", "Zombii",
			"tymoteus", "skorpion_ariana", "кнопсик", "pusjenok",
			"Prikoljnaja", "Deffka_N", "666violence666", "modirator", "VISSS",
			"DIANA66", "svoj_sredi___", "yl2eo", "гюрза69", "Natali0416",
			"olenjka753", "Meguro", "shalion", "К.Воробьянинов", "jablo4ko:)",
			"ariska555", "мой_брат_фрик", "irchita", "ket2008", "potretuznik",
			"Kittie", "kotenok_lv", "utochka7", "KatjaR", "Putriki", "njuha",
			"Deadly_Nymph", "susurinjsh", "ERRAST", "Hamet", "OldChap",
			"€urokуkя", "ilana911", "gurija", "helena66", "Casualspirit",
			"Drongo666", "†ragicomedy", "Littery", "Vereja27", "Puss_in_Boots",
			"powwa", "leblanc", "MisterMaloj", "MementoMori", "Vils",
			"MaScaRaD", "_silence_23", "PilotsTims", "Evo4k@26", "krista^girl",
			"karinaa", "Tairi", "burzu", "anetik", "credendo", "Nimfetochka",
			"Sollya", "Aneto4ka", "_ri6ka_", "ExTRlM", "Alinusjka", "V!mparka",
			"rohus", "KaCuK", "Palex60", "Stashevskey", "Fancyrat", "lika56",
			"saulite25", "f_sunshine", "Alabama30", "lase", "teack",
			"fotoramka", "Nikolajs79", "zaika2008", "Marsella-Junik",
			"Degrand_", "Yamilia", "Sun_Ray", "kasiopeja", "VIESTURS",
			"Paprica", "Dontsov.1954", "lipdan", "lidusjan", "arvivil",
			"JaLanaja", "Johnson", "cezik", "adidasss", "Murmulīte",
			"antoshka9", "Distorted_Doll]", "[Just_Marix]", "enigma30", "zuy",
			"ЗВОНОК", "Angel_Picasso", "kostikkon", "svetvokne", "HamonRat",
			"malina.lv", "sambaParty", "Nastja3214", "МАКС_ЧУДОВ",
			"Lenusik004", "futbolistka", "Шедоу", "забавный_зверёк", "Izabelj",
			"Romires", "Niki_mephisto", "raduznaja", "Airhorse", "DR.vatson",
			"bukurija777", "Leon222", "alergo", "Allowed_to_live", "Farmer",
			"olegzan", "liza97d", "fargus59", "kirochka", "девочКа_счастЬя",
			"vladisb", "100Million", "krajevaJelena", "valostin",
			"пушкарева_катя", "Furmanov", "megy300", "Zojaa", "Degrik",
			"Buljka", "djvol_vselenoy", "inj", "tostka", "dzulieta",
			"madfreak", "N_a_n_A", "Kjarrra", "milawka665", "ПЛАТИНА",
			"Nabljudatel", "slastjona", "Daniela1990", "vilorr", "Krisssi",
			"Lana18", "Valve", "Ivan97", "Dora_", "riwanka", "lina014",
			"zarina2610", "djaa", "tanyu6ka", "Cheshka_89", "katttja",
			"cippa000", "ezermalas", "drugan", "shakalka", "studentkaTSI",
			"Bomba4o", "women_in_black", "dima&rita", "Panteleev", "Ain.Bro",
			"ConstB", "Никитосик", "karosia", "eriksons", "kristers-tridub",
			"Volum", "Xavro6e4ka", "edclip", "6uni4ka", "negus1", "vika12345",
			"jeo62", "Sirenia", "Весенняя_Белка", "viva00", "Anna114", "mio69",
			"Kristina_2000", "Ksju6enjka", "oga", "_JoCKeR_", "Galateja",
			"ILonika", "Modzhozi", "yulawka", "svetik67", "АудринеЦ",
			"bikycik", "Razvratnica1978", "magik-63", "Ahoj!", "_OlikA_",
			"_shkoljnica_", "klubnika007", "лигита", "Vljubljenij", "Re-бусс",
			"k_a_t_e_n_k_a", "kucakyky", "Atium", "PUOPS", "Miledija", "tenna",
			"diddle", "cucelka", "anabella04", "_Манька_", "Kristilanochka",
			"bezlogina", "Magnoli_a", "LadyEmily", "o4irova6ka", "vona",
			"4elsi", "lu057", "Alberts", "Natasha2", "Реформа",
			"andreevandrej", "vika222", "Meljhior", "magav8", "tolk", "Жози",
			"gubars", "Anazapta", "катенак", "jankamenb", "Ne_Shaxmatistka",
			"29_katja_01", "abi_gail", "qsti", "Rafaella1", "ninanina",
			"natik23", "Lesja_83", "Tigrica_vikija", "N1ka.", "Lady-MarmeLady",
			"GfRed", "JIuJIuT", "angelmik", "irvoslan", "alenushkin",
			"Bastionka", "nebeda", "LevKor", "Лада7", "andr5", "dislike",
			"ExiRouS", "OKN", "KuCbKa", "diona86", "_LiTo4ka_", "Anakonda4ka",
			"xali-20", "Markezy", "Ayamme", "natu1", "ivetta9ezer", "sondor",
			"Magic-Mistress", "ADelinocka", "_SADISTKA_", "In_Spe", "kreole",
			"Ksana.Love", "h2orikki", "Ver.Aleks", "Лапулечка", "Lakeplacid",
			"LeDiJa", "Magika", "jaanis", "Molekula12", "keja",
			"Angel_of_lighT", "lerusjka", "rasomaxa", "kesharik", "Anlika",
			"Chehov", "tageraskina", "elisja", "Esir", "Anzuka", "vkusnyj",
			"Alex (Mauyri-sama) Leon", "BoBeR", "Гламурная", "Xsander", "WЫЁ",
			"Kazairl", "martin", "Vlubchivaya", "Падшая графиня",
			"OJDSVB47w9vbRVBv43987vbORVB953", "Басист", "Black_raindeer",
			"Lkv", "Namiko_Mokona", "Ямайка", "My Personal God", "Tanokura",
			"bezprava", "Летучая Крысь", "el clasico", "Silebrity", "R@M@$hk@",
			"Валафия", "Aunt_Ko", "Chidori-Hime", "Улинталу", "hapkom",
			"Walterka", "Китайский принц-полукровка", "Motoko-tama",
			"Repressor Of Memories", "bichva", "Sett", "Amateratsu", "Kasyak",
			"Impulse", "Marti", "FADDY", "Дели", "Thefar", "myxa",
			"andrian-ice", "ReZoN", "Мечтать Не Вредно", "Luna", "StrikerXP",
			"Saere", "bluesman", "EL", "Exile", "InsaneClown", "Andron",
			"Молер", "Socket", "Balinese", "Lover Ran", "VikeW", "Nike Felias",
			"S-Sa", "bi-666", "Аэ", "Sylar", "Mild", "Люминар Светозар Гало",
			"Faribu", "kofus", "AkaiYoru", "Greys", "Миллер", "Niellune",
			"[rec.]", "hondurazian", "Relinda", "Helgara", "[•Cocaine•]",
			"Larser", "Ось Колкая", "Fairy_Violin", "yА", "Warrior4000",
			"Atar", "Morween", "ladaemi", "xelik", "Justa", "Kallisto",
			"Triam", "Тамерлан", "Heretik", "Лас Соната", "0nizuka", "FINTER",
			"Compost", "Екзарх", "Herucime", "Сорен", "Lorana", "Ведьма Лин",
			"Генжи", "Gromint", "Batofgrave", "Burstfire", "Melhisedek",
			"Lais", "HeRMiT", "RinQ", "FegORe", "Анна Белински", "len'thik",
			"GraftVirion", "Dj Van Der FuL", "AHTuXPuCT", "catarinka",
			"Ar4erS", "branix", "ReddRatt", "Дерион", "frozz :E", "Vadimz",
			"monteo", "lexsy", "Серебряный", "Вагарул", "Moroz", "kadze_san",
			"Radon", "Timeo", "Хелисента Хильдебрандт", "Эми Рональд",
			"Non_limited in love", "Halyava", "DemigoD", "Лейко Кусаги", "DIX",
			"Kind Hamster", "Oblivion_rules", "Parovoz", "gawwial", "netiv",
			"Soulforger", "Liss", "k0hфЭ:D'k0", "MassaНя", "Chik0",
			"IceShadow", "not girl", "Ma3a", "jii", "komesk", "MiyaBi" };

	@Autowired
	private GameService gameService;

	private ScoreService scoreService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private UserService userService;

	private void addBot(Integer gameId) {
		Random generator = new Random(System.currentTimeMillis());
		Game game = gameService.fetch(gameId);

		String[] names = getNicknames(game.getCountry());
		String name = names[generator.nextInt(names.length)];
		int t = names.length;

		while (isBotAlreadyPlaying(game, name)) {
			name = names[generator.nextInt(names.length)];
			t--;
			if (t <= 0)
				return;
		}

		LOG.info("Add bot with name: " + name + " to game: " + gameId);
		addBot(name, null, gameId);
	}

	@Override
	public void addBot(String name, Integer botId, Integer gameId) {
		Game game = gameService.fetch(gameId);
		User bot = null;

		if (botId != null) {
			bot = userService.fetch(botId);
		} else {
			bot = userService.fetch(name);
			if (bot == null) {
				bot = new User();
				bot.setAddress("localhost");
				bot.setCity("localhost");
				bot.setEmail("bot@gobro.net");
				bot.setFirstName("Bot");
				bot.setLastName(name);
				bot.setLogin(name);
				bot.setPasswd("099b3b060154898840f0ebdfb46ec78f");
				bot.setPhoneNumber("localhost");
				bot.setRole(RoleEnum.BOT);
				bot.setUserPlays(null);
				bot.setZip("localhost");
				bot.setCountry(game.getCountry());

				try {
					bot = userService.store(bot);
				} catch (Exception e) {
					LOG.error("Can not add bot: " + name + " to game: "
							+ gameId);
					return;
				}
			}
		}

		if (bot != null) {
			gameService.join(game, bot, Calendar.getInstance(), false);
		}
	}

	@Override
	public void addBots(Integer gameId) {
		Random generator = new Random(System.currentTimeMillis());
		int c = generator.nextInt(100);
		Game game = gameService.fetch(gameId);
		String[] names = getNicknames(game.getCountry());

		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			User user = userService.fetch(name);
			if (user != null) {
				if (user.getRole() != RoleEnum.BOT)
					continue;

				if (isBotAlreadyPlaying(game, name))
					continue;
			}
			try {
				addBot(name, null, gameId);
			} catch (Exception e) {
				continue;
			}
			c--;
			if (c <= 0)
				break;
		}
	}

	@Override
	public void addRandomBot() {
		List<CountryEnum> countries = supportedLanguagesService.getCountries();
		for (CountryEnum countryEnum : countries) {
			List<Game> games = gameService.fetchGamesOpenedForBidding(
					countryEnum, Calendar.getInstance());

			for (Game game : games) {
				try {
					addBot(game.getId());
				} catch (Exception e) {
					// just skip this turn is a case of error
				}
			}
		}
	}

	@Override
	public void addRandomPoints(Integer gameId, List<ScoreRule> scoreRules) {
		// fetch users registered in the game
		Game game = gameService.fetch(gameId);

		if (game.getUserPlays() != null) {
			for (UserPlay userPlay : game.getUserPlays()) {
				if (userPlay.isDisabled()
						|| userPlay.getUser().getRole() != RoleEnum.BOT)
					continue;

				addRandomPointsToOne(scoreRules, userPlay);
			}
		}
	}

	private int addRandomPointsToOne(List<ScoreRule> scoreRules,
			UserPlay userPlay) {
		Random generator = new Random(System.currentTimeMillis());
		int r = generator.nextInt(scoreRules.size() + 1);

		if (r < scoreRules.size()) {
			ScoreRule scoreRule = scoreRules.get(r);
			Sms sms = smsService.createFakeSms(userPlay.getAlias(), scoreRule);
			smsService.store(sms, false);
		}
		return r;
	}

	@Override
	public void addRandomPointsToRandomOne() {
		List<CountryEnum> countries = supportedLanguagesService.getCountries();
		for (CountryEnum countryEnum : countries) {
			List<Game> games = gameService.fetchGamesOpenedForBidding(
					countryEnum, Calendar.getInstance());
			List<ScoreRule> scoreRules = scoreService
					.getScoreRules(countryEnum);

			for (Game game : games) {
				addRandomPointsToRandomOne(game.getId(), scoreRules);
			}
		}
	}

	@Override
	public void addRandomPointsOnFinish() {
		List<CountryEnum> countries = supportedLanguagesService.getCountries();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.HOUR, 12);
		calendar = DateUtils.truncate(calendar, Calendar.HOUR);

		for (CountryEnum countryEnum : countries) {
			List<Game> games = gameService.fetchGamesOpenedForBidding(
					countryEnum, calendar);
			List<ScoreRule> scoreRules = scoreService
					.getScoreRules(countryEnum);

			for (Game game : games) {
				addRandomPointsToRandomOne(game.getId(), scoreRules);
			}
		}
	}

	@Override
	public void addRandomPointsToRandomOne(Integer gameId,
			List<ScoreRule> scoreRules) {
		// fetch users registered in the game
		Game game = gameService.fetch(gameId);
		Random generator = new Random(System.currentTimeMillis());

		if (game.getUserPlays() != null) {
			List<UserPlay> userPlays = game.getUserPlays();
			if (userPlays.size() == 0)
				return;
			UserPlay userPlay = null;
			do {
				userPlay = userPlays.get(generator.nextInt(userPlays.size()));
			} while (userPlay.isDisabled()
					|| userPlay.getUser().getRole() != RoleEnum.BOT);

			addRandomPointsToOne(scoreRules, userPlay);
		}
	}

	private String[] getNicknames(CountryEnum country) {
		switch (country) {
		case LV:
			return nicknamesLV;
		case LT:
			return nicknamesLT;
		}
		return nicknamesEE;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	private boolean isBotAlreadyPlaying(Game game, String name) {
		List<UserPlay> userPlays = game.getUserPlays();
		for (UserPlay userPlay : userPlays) {
			if (userPlay.getUser().getLogin().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
}