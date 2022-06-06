package com.example.airbnb.ui.guestRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.airbnb.R
import com.example.airbnb.ui.information.InformationViewModel

class GuestRangeFragment : Fragment() {

    private val viewModel: InformationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    GuestRangeView(viewModel)
                }
            }
        }
    }
}

@Composable
fun GuestRangeView(viewModel: InformationViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            var adultCount = viewModel.adultCountStateFlow.collectAsState().value
            var childCount = viewModel.childCountStateFlow.collectAsState().value
            var toddlerCount = viewModel.toddlerCountStateFlow.collectAsState().value
            GuestInputHeader(
                input = "인원 입력",
                guest = "게스트 ",
                child = "유아 ",
                guestCount = (adultCount + childCount),
                toddlerCount = toddlerCount
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                GuessAdultSelectorLayout(adultCount) {
                    if (it > 0) {
                        viewModel.setSkipFlagFalse()
                        viewModel.setCheckFlagTrue()
                    } else {
                        viewModel.setCheckFlagFalse()
                    }
                    viewModel.saveAdultCount(it)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp), color = Color(0xFFE0E0E0)
                )
                GuessChildSelectorLayout(childCount) {
                    if (it > 0) {
                        viewModel.setSkipFlagFalse()
                        //성인이 0명인 상태에서 증가버튼이 눌렸을때
                        if (adultCount == 0 && childCount < it) {
                            viewModel.increaseAdultCountByChildOrToddler(1)
                        }
                    }
                    viewModel.saveChildCount(it)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp), color = Color(0xFFE0E0E0)
                )
                GuessToddlerSelectorLayout(toddlerCount) {
                    if (it > 0) {
                        viewModel.setSkipFlagFalse()
                        //성인이 0명인 상태에서 증가버튼이 눌렸을때
                        if (adultCount == 0 && toddlerCount < it) {
                            viewModel.increaseAdultCountByChildOrToddler(1)
                        }
                    }
                    viewModel.saveToddlerCount(it)
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
            .wrapContentHeight(Alignment.Top)
            .background(Color(0xFFF5F5F7))
            .padding(start = 76.dp, bottom = 22.dp)
    ) {
        Text(text = input, style = MaterialTheme.typography.overline)
        Text(
            text = "${guest} ${guestCount}, ${child} ${toddlerCount}",
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun GuestSelector(type: String, ageRange: String, countNumber: Int,onCountChanged: (countNumber: Int) -> Unit) {
    var countingNumber = countNumber
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
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
                onCountChanged(countingNumber)
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
            IconButton(enabled = countNumber < 8, onClick = {
                countingNumber += 1
                onCountChanged(countingNumber)
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


@Composable
fun GuessAdultSelectorLayout(countNumber: Int, onCountChanged: (countNumber: Int) -> Unit) {
    GuestSelector(type = "성인", ageRange = "만13세이상", countNumber, onCountChanged)
}

@Composable
fun GuessChildSelectorLayout(countNumber: Int, onCountChanged: (countNumber: Int) -> Unit) {
    GuestSelector(type = "어린이", ageRange = "만2~13세", countNumber, onCountChanged)
}

@Composable
fun GuessToddlerSelectorLayout(countNumber: Int,  onCountChanged: (countNumber: Int) -> Unit) {
    GuestSelector(type = "유아", ageRange = "만2세미만", countNumber, onCountChanged)

}

