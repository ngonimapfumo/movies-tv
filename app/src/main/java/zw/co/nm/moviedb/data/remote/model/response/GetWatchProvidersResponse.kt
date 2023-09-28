package zw.co.nm.moviedb.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class GetWatchProvidersResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results
) {
    data class Results(
        @SerializedName("AD")
        val aD: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AD,
        @SerializedName("AE")
        val aE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AE,
        @SerializedName("AL")
        val aL: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AL,
        @SerializedName("AO")
        val aO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AO,
        @SerializedName("AR")
        val aR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AR,
        @SerializedName("AT")
        val aT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AT,
        @SerializedName("AU")
        val aU: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AU,
        @SerializedName("BA")
        val bA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BA,
        @SerializedName("BE")
        val bE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BE,
        @SerializedName("BF")
        val bF: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BF,
        @SerializedName("BG")
        val bG: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BG,
        @SerializedName("BO")
        val bO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BO,
        @SerializedName("BR")
        val bR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BR,
        @SerializedName("BZ")
        val bZ: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BZ,
        @SerializedName("CA")
        val cA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CA,
        @SerializedName("CH")
        val cH: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CH,
        @SerializedName("CL")
        val cL: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CL,
        @SerializedName("CO")
        val cO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CO,
        @SerializedName("CR")
        val cR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CR,
        @SerializedName("CY")
        val cY: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CY,
        @SerializedName("CZ")
        val cZ: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CZ,
        @SerializedName("DE")
        val dE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DE,
        @SerializedName("DK")
        val dK: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DK,
        @SerializedName("DO")
        val dO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DO,
        @SerializedName("EC")
        val eC: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EC,
        @SerializedName("EE")
        val eE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EE,
        @SerializedName("EG")
        val eG: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EG,
        @SerializedName("ES")
        val eS: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ES,
        @SerializedName("FI")
        val fI: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FI,
        @SerializedName("FJ")
        val fJ: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FJ,
        @SerializedName("FR")
        val fR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FR,
        @SerializedName("GB")
        val gB: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GB,
        @SerializedName("GR")
        val gR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GR,
        @SerializedName("GT")
        val gT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GT,
        @SerializedName("HK")
        val hK: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HK,
        @SerializedName("HN")
        val hN: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HN,
        @SerializedName("HR")
        val hR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HR,
        @SerializedName("HU")
        val hU: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HU,
        @SerializedName("ID")
        val iD: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ID,
        @SerializedName("IE")
        val iE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IE,
        @SerializedName("IL")
        val iL: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IL,
        @SerializedName("IN")
        val iN: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IN,
        @SerializedName("IS")
        val iS: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IS,
        @SerializedName("IT")
        val iT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IT,
        @SerializedName("JP")
        val jP: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.JP,
        @SerializedName("KR")
        val kR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.KR,
        @SerializedName("LI")
        val lI: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LI,
        @SerializedName("LT")
        val lT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LT,
        @SerializedName("LU")
        val lU: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LU,
        @SerializedName("LV")
        val lV: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LV,
        @SerializedName("MD")
        val mD: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MD,
        @SerializedName("ME")
        val mE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ME,
        @SerializedName("MK")
        val mK: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MK,
        @SerializedName("MT")
        val mT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MT,
        @SerializedName("MX")
        val mX: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MX,
        @SerializedName("MY")
        val mY: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MY,
        @SerializedName("NE")
        val nE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NE,
        @SerializedName("NI")
        val nI: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NI,
        @SerializedName("NL")
        val nL: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NL,
        @SerializedName("NO")
        val nO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NO,
        @SerializedName("NZ")
        val nZ: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NZ,
        @SerializedName("PA")
        val pA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PA,
        @SerializedName("PE")
        val pE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PE,
        @SerializedName("PH")
        val pH: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PH,
        @SerializedName("PL")
        val pL: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PL,
        @SerializedName("PT")
        val pT: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PT,
        @SerializedName("PY")
        val pY: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PY,
        @SerializedName("RO")
        val rO: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RO,
        @SerializedName("RS")
        val rS: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RS,
        @SerializedName("RU")
        val rU: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RU,
        @SerializedName("SA")
        val sA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SA,
        @SerializedName("SE")
        val sE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SE,
        @SerializedName("SG")
        val sG: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SG,
        @SerializedName("SI")
        val sI: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SI,
        @SerializedName("SK")
        val sK: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SK,
        @SerializedName("SM")
        val sM: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SM,
        @SerializedName("SN")
        val sN: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SN,
        @SerializedName("SV")
        val sV: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SV,
        @SerializedName("TH")
        val tH: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TH,
        @SerializedName("TR")
        val tR: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TR,
        @SerializedName("TW")
        val tW: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TW,
        @SerializedName("TZ")
        val tZ: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TZ,
        @SerializedName("UA")
        val uA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UA,
        @SerializedName("UG")
        val uG: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UG,
        @SerializedName("US")
        val uS: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.US,
        @SerializedName("UY")
        val uY: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UY,
        @SerializedName("VE")
        val vE: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.VE,
        @SerializedName("ZA")
        val zA: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZA,
        @SerializedName("ZM")
        val zM: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZM,
        @SerializedName("ZW")
        val zW: zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZW
    ) {
        data class AD(
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AD.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AE.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AL.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AL.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AL.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AO.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AO.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AR.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AR.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AT.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AT.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AT.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AU.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AU.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.AU.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BA.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BF.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BF.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BG.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BG.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BG.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BO.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BR.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.BZ.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CA.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CA.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CA.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CH.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CH.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CH.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CL.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CL.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CL.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CO.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CO.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CO.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CR.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CY.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CY.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CZ.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CZ.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.CZ.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DK.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DK.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DK.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.DO.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EC.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EC.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EC.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EG.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.EG.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ES.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ES.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ES.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FI.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FI.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FI.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FJ.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FJ.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FR.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.FR.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GB.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GB.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GB.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GR.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GR.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.GT.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HK.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HN.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HR.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HR.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HU.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HU.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.HU.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ID.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IL.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IL.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IN.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IN.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IN.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IS.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IS.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IS.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IT.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IT.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.IT.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.JP.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.JP.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.JP.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.KR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.KR.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LI.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LT.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LT.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LT.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LU.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LU.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LU.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LV.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LV.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.LV.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MD.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MD.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ME.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MK.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MT.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MT.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MT.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MX.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.MY.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NE.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NE.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NI.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NL.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NL.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NL.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NO.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NO.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NO.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NZ.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NZ.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.NZ.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PA.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PE.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PH.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PL.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PL.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PL.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PT.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PT.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PT.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.PY.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RO.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RS.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RU.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RU.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.RU.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SA.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SA.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SE.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SG.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SI.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SI.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SI.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SK.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SK.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SK.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SM.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SN.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SN.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.SV.Flatrate>,
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TH.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TR.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TR.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TR.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TW.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TZ.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.TZ.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UA.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UA.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UG.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UG.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.US.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.US.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.US.Rent>
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
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.UY.Flatrate>,
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.VE.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.VE.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.VE.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZA.Buy>,
            @SerializedName("flatrate")
            val flatrate: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZA.Flatrate>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZA.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZM.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZM.Rent>
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
            val buy: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZW.Buy>,
            @SerializedName("link")
            val link: String,
            @SerializedName("rent")
            val rent: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results.ZW.Rent>
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