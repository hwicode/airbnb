package com.example.airbnb.ui.guestRange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.airbnb.R


class GuestRangeFragment : Fragment() {


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

}

@Composable
fun GuestRangeView() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column() {
            GuestInputHeader(input = "인원 입력", guest = "게스트 0", child = "유아 0")
            GuestSelector("성인", "만 13세 이상")
        }
    }

}

@Composable
fun GuestInputHeader(input: String, guest: String, child: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(58.dp)
            .wrapContentHeight(Alignment.Top)
            .background(Color(0xFFF5F5F7))
            .padding(start = 76.dp, bottom = 22.dp)
    ) {
        Text(text = input, style = MaterialTheme.typography.overline)
        Text(text = "${guest}, $child", style = MaterialTheme.typography.h6)
    }
}

@Composable
fun GuestSelector(type: String, ageRange: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = type, style = MaterialTheme.typography.subtitle1)
            Text(text = ageRange, style = MaterialTheme.typography.subtitle2, color = Color(0xFF828282))
        }
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            var countNumber: Int = 0
            Spacer(modifier = Modifier.height(21.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove_circle_outline_black_24dp),
                    contentDescription = "minus_selector_btn",
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(text = "$countNumber", modifier = Modifier.wrapContentSize(Alignment.Center))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle_outline_black_24dp),
                    contentDescription = "plus_selector_btn",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun GuestInputHeaderPreview() {
    MaterialTheme {
        GuestRangeView()
    }
}