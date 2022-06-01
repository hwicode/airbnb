package com.example.airbnb.ui.guestRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.airbnb.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class GuestRangeFragment : Fragment() {
    private lateinit var skipBtn: Button
    private lateinit var nextBtn: ImageButton
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    GuestRangeView()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skipBtn = view.rootView.findViewById(R.id.btn_information_skip)
        nextBtn = view.rootView.findViewById(R.id.iBtn_information_next)
        navigator= Navigation.findNavController(view)

        nextBtn.setOnClickListener {
            navigator.navigate(R.id.action_guestRangeFragment_to_calendarFragment)
        }
        skipBtn.setOnClickListener {
            navigator.navigate(R.id.action_guestRangeFragment_to_calendarFragment)
        }
    }

}

@Composable
fun GuestRangeView() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column() {
            var adultCount by rememberSaveable { mutableStateOf(0) }
            var childCount by rememberSaveable { mutableStateOf(0) }
            var toddlerCount by rememberSaveable { mutableStateOf(0) }
            GuestInputHeader(input = "인원 입력", guest = "게스트 ", child = "유아 ", guestCount= (adultCount + childCount), toddlerCount = toddlerCount)

            Column(modifier = Modifier
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp)) {
                GuessAdultSelectorLayout(adultCount) {
                    adultCount = it
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = Color(0xFFE0E0E0 ))
                GuessChildSelectorLayout(childCount) {
                    childCount = it
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = Color(0xFFE0E0E0 ))
                GuessToddlerSelectorLayout(toddlerCount) {
                    toddlerCount = it
                }
            }
        }
    }

}

@Composable
fun GuestInputHeader(input: String, guest: String, child: String, guestCount: Int, toddlerCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(58.dp)
            .wrapContentHeight(Alignment.Top)
            .background(Color(0xFFF5F5F7))
            .padding(start = 76.dp, bottom = 22.dp)
    ) {
        Text(text = input, style = MaterialTheme.typography.overline)
        Text(text = "${guest} ${guestCount}, ${child} ${toddlerCount}", style = MaterialTheme.typography.h6)
    }
}

@Composable
fun GuestSelector(type: String, ageRange: String, countNumber: Int, count: (countNumber: Int) -> Unit) {
    var countingNumber = countNumber
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp)
        ,
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.5F)) {

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = type, style = MaterialTheme.typography.subtitle1)
            Text(
                text = ageRange,
                style = MaterialTheme.typography.subtitle2,
                color = Color(0xFF828282)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Top)
        ) {
            IconButton(enabled = countingNumber != 0, onClick = {
                countingNumber -= 1
                count(countingNumber)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove_circle_outline_black_24dp),
                    contentDescription = "minus_selector_btn",
                    modifier = Modifier.size(35.dp)
                )
            }
            Text(
                text = "$countNumber",
                modifier = Modifier.wrapContentSize(Alignment.Center),
                style = MaterialTheme.typography.h6,
            )
            IconButton(onClick = {
                countingNumber += 1
                count(countingNumber)
            })
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle_outline_black_24dp),
                    contentDescription = "plus_selector_btn",
                    modifier = Modifier.size(35.dp)
                )
            }

        }
    }
}

/*@Composable
fun GuessSelectorLayout(countNumber: Int, count: (isPlus: Boolean) -> Unit){
    Column(modifier = Modifier
        .wrapContentHeight()
        .padding(start = 16.dp, end = 16.dp)) {
        GuestSelector(type = "성인", ageRange = "만13세이상", countNumber, count)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = Color(0xFFE0E0E0 ))
        GuestSelector(type = "어린이", ageRange = "만2~13세", countNumber, count)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = Color(0xFFE0E0E0 ))
        GuestSelector(type = "유아", ageRange = "만2세 미만", countNumber, count)
    }
}*/

@Composable
fun GuessSelectorLayout(adultCount: Int, childCount: Int, toddlerCount: Int, count: (countNumber: Int) -> Unit) {
    Column(modifier = Modifier
        .wrapContentHeight()
        .padding(start = 16.dp, end = 16.dp)) {
        GuessAdultSelectorLayout(adultCount, count)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = Color(0xFFE0E0E0 ))
        GuessChildSelectorLayout(childCount, count)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp), color = Color(0xFFE0E0E0 ))
        GuessToddlerSelectorLayout(toddlerCount, count)
    }
}

@Composable
fun GuessAdultSelectorLayout(countNumber: Int, count: (countNumber: Int) -> Unit) {
    GuestSelector(type = "성인", ageRange = "만13세이상", countNumber, count)
}

@Composable
fun GuessChildSelectorLayout(countNumber: Int, count: (countNumber: Int) -> Unit) {
    GuestSelector(type = "성인", ageRange = "만13세이상", countNumber, count)
}

@Composable
fun GuessToddlerSelectorLayout(countNumber: Int, count: (countNumber: Int) -> Unit) {
    GuestSelector(type = "성인", ageRange = "만13세이상", countNumber, count)
}

@Composable
@Preview(showBackground = true)
fun GuestInputHeaderPreview() {
    MaterialTheme {
        GuestRangeView()
    }
}

