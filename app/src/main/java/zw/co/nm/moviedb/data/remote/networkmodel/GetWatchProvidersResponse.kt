package zw.co.nm.moviedb.data.remote.networkmodel


import com.google.gson.annotations.SerializedName

data class GetWatchProvidersResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: Results
) {
    data class Results(
        @SerializedName("AD")
        val aD: AD,
        @SerializedName("AE")
        val aE: AE,
        @SerializedName("AL")
        val aL: AL,
        @SerializedName("AO")
        val aO: AO,
        @SerializedName("AR")
        val aR: AR,
        @SerializedName("AT")
        val aT: AT,
        @SerializedName("AU")
        val aU: AU,
        @SerializedName("BA")
        val bA: BA,
        @SerializedName("BE")
        val bE: BE,
        @SerializedName("BF")
        val bF: BF,
        @SerializedName("BG")
        val bG: BG,
        @SerializedName("BO")
        val bO: BO,
        @SerializedName("BR")
        val bR: BR,
        @SerializedName("BZ")
        val bZ: BZ,
        @SerializedName("CA")
        val cA: CA,
        @SerializedName("CH")
        val cH: CH,
        @SerializedName("CL")
        val cL: CL,
        @SerializedName("CO")
        val cO: CO,
        @SerializedName("CR")
        val cR: CR,
        @SerializedName("CY")
        val cY: CY,
        @SerializedName("CZ")
        val cZ: CZ,
        @SerializedName("DE")
        val dE: DE,
        @SerializedName("DK")
        val dK: DK,
        @SerializedName("DO")
        val dO: DO,
        @SerializedName("EC")
        val eC: EC,
        @SerializedName("EE")
        val eE: EE,
        @SerializedName("EG")
        val eG: EG,
        @SerializedName("ES")
        val eS: ES,
        @SerializedName("FI")
        val fI: FI,
        @SerializedName("FJ")
        val fJ: FJ,
        @SerializedName("FR")
        val fR: FR,
        @SerializedName("GB")
        val gB: GB,
        @SerializedName("GR")
        val gR: GR,
        @SerializedName("GT")
        val gT: GT,
        @SerializedName("HK")
        val hK: HK,
        @SerializedName("HN")
        val hN: HN,
        @SerializedName("HR")
        val hR: HR,
        @SerializedName("HU")
        val hU: HU,
        @SerializedName("ID")
        val iD: ID,
        @SerializedName("IE")
        val iE: IE,
        @SerializedName("IL")
        val iL: IL,
        @SerializedName("IN")
        val iN: IN,
        @SerializedName("IS")
        val iS: IS,
        @SerializedName("IT")
        val iT: IT,
        @SerializedName("JP")
        val jP: JP,
        @SerializedName("KR")
        val kR: KR,
        @SerializedName("LI")
        val lI: LI,
        @SerializedName("LT")
        val lT: LT,
        @SerializedName("LU")
        val lU: LU,
        @SerializedName("LV")
        val lV: LV,
        @SerializedName("MD")
        val mD: MD,
        @SerializedName("ME")
        val mE: ME,
        @SerializedName("MK")
        val mK: MK,
        @SerializedName("MT")
        val mT: MT,
        @SerializedName("MX")
        val mX: MX,
        @SerializedName("MY")
        val mY: MY,
        @SerializedName("NE")
        val nE: NE,
        @SerializedName("NI")
        val nI: NI,
        @SerializedName("NL")
        val nL: NL,
        @SerializedName("NO")
        val nO: NO,
        @SerializedName("NZ")
        val nZ: NZ,
        @SerializedName("PA")
        val pA: PA,
        @SerializedName("PE")
        val pE: PE,
        @SerializedName("PH")
        val pH: PH,
        @SerializedName("PL")
        val pL: PL,
        @SerializedName("PT")
        val pT: PT,
        @SerializedName("PY")
        val pY: PY,
        @SerializedName("RO")
        val rO: RO,
        @SerializedName("RS")
        val rS: RS,
        @SerializedName("RU")
        val rU: RU,
        @SerializedName("SA")
        val sA: SA,
        @SerializedName("SE")
        val sE: SE,
        @SerializedName("SG")
        val sG: SG,
        @SerializedName("SI")
        val sI: SI,
        @SerializedName("SK")
        val sK: SK,
        @SerializedName("SM")
        val sM: SM,
        @SerializedName("SN")
        val sN: SN,
        @SerializedName("SV")
        val sV: SV,
        @SerializedName("TH")
        val tH: TH,
        @SerializedName("TR")
        val tR: TR,
        @SerializedName("TW")
        val tW: TW,
        @SerializedName("TZ")
        val tZ: TZ,
        @SerializedName("UA")
        val uA: UA,
        @SerializedName("UG")
        val uG: UG,
        @SerializedName("US")
        val uS: US,
        @SerializedName("UY")
        val uY: UY,
        @SerializedName("VE")
        val vE: VE,
        @SerializedName("ZA")
        val zA: ZA,
        @SerializedName("ZM")
        val zM: ZM,
        @SerializedName("ZW")
        val zW: ZW
    ) {
        data class AD(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AL(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AO(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AT(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class AU(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BA(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BF(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BG(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BO(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BR(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class BZ(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CA(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CH(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CL(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CO(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CR(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CY(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class CZ(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class DE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class DK(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class DO(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class EC(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class EE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class EG(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ES(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class FI(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class FJ(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class FR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class GB(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class GR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class GT(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class HK(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class HN(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class HR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class HU(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ID(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class IE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class IL(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class IN(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class IS(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class IT(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class JP(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class KR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class LI(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class LT(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class LU(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class LV(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class MD(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ME(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class MK(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class MT(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class MX(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class MY(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class NE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class NI(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class NL(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class NO(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class NZ(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PA(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PH(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PL(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PT(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class PY(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class RO(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class RS(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class RU(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SA(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SG(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SI(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SK(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SM(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SN(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class SV(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class TH(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class TR(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class TW(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class TZ(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class UA(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class UG(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class US(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class UY(
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String
        ) {
            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class VE(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ZA(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("flatrate")
            val flatrate: List<Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Flatrate(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ZM(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }

        data class ZW(
            @SerializedName("buy")
            val buy: List<Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<Rent>
        ) {
            data class Buy(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )

            data class Rent(
                @SerializedName("display_priority")
                val displayPriority: Int,
                @SerializedName("logo_path")
                val logoPath: String,
                @SerializedName("provider_id")
                val providerId: Int,
                @SerializedName("provider_name")
                val providerName: String
            )
        }
    }
}