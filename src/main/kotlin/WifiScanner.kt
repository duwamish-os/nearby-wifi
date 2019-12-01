import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

public class WifiScanner {

    companion object {
        val MAC_ADDRESS_1 = "^([a-fA-F0-9][:-]){5}[a-fA-F0-9][:-]$"
        val MAC_ADDRESS_2 = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$"
        val MAC_ADDRESS = "^[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}$"
        val MAC_ADDRESS_REGEX = Pattern.compile(MAC_ADDRESS_2)

        @JvmStatic
        fun main(args: Array<String>) {
            val command = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/A/Resources/airport scan"

            val ps = Runtime.getRuntime().exec(command)
            val psOutput = BufferedReader(InputStreamReader(ps.inputStream))
            var line = psOutput.readLine()
            var init = 0
            while (line != null) {
                if (init > 0) {
                    val wifi = line.trim().replace(MAC_ADDRESS, "MAC_ADDRESS")
                    println("Has mac " + "a0:63:91:a3:d8:d8".matches(MAC_ADDRESS_REGEX.toRegex()))
                    println("" + wifi)
                    val wifiValues = wifi.split(MAC_ADDRESS_1)
                    println(wifiValues[0] + " " + wifiValues[2])
                }
                line = psOutput.readLine()
                init += 1
            }

        }
    }
}
