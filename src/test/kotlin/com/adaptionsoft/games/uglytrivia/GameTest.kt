package com.adaptionsoft.games.uglytrivia

import com.adaptionsoft.games.trivia.runner.GameRunner
import com.adaptionsoft.games.trivia.runner.main
import org.junit.Assert.*
import org.junit.Test
import java.io.*
import java.util.*


class GameTest {
    @Test
    fun golden_master() {
        GameRunner.getRandom = {  Random(1) }

        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val old = System.out
        System.setOut(ps)

        main(arrayOf())

        val actual = baos.toString()

        System.out.flush()
        System.setOut(old)

        val inputStream: InputStream = File("out.txt").inputStream()
        val expected = inputStream.bufferedReader().use { it.readText() }

        assertEquals(expected, actual)

    }
}