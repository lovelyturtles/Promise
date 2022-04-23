//package comp3350.group6.promise.presentation.Project.Invitation.SendInvite;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//
//import java.util.List;
//
//import comp3350.group6.promise.R;
//import comp3350.group6.promise.objects.AccountUser;
//import comp3350.group6.promise.presentation.User.AccountUserAdapter;
//
//public class UserSelectorFragment extends Fragment {
//
//    private EditText searchInput;
//    private RecyclerView searchListRecycler;
//
//    private UserSelectorViewModel model;
//
//    public UserSelectorFragment() {
//        super(R.layout.user_selector_fragment);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        model = new ViewModelProvider(getParentFragment()).get(UserSelectorViewModel.class);
//
//        searchInput = view.findViewById( R.id.userSearch);
//        searchListRecycler = view.findViewById( R.id.user_search_result_list );
//
//        // Setup search list
//
//        AccountUserAdapter searchListAdapter = new AccountUserAdapter(getContext(), model.getSearchResultUsers().getValue(), new SearchedUserClickListener(model));
//        searchListRecycler.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
//        searchListRecycler.setAdapter(searchListAdapter);
//
//        // Setup search input functionality
//
//        searchInput.addTextChangedListener(new TextWatcher() {
//            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String searchTerm = editable.toString();
//                model.searchUsers(searchTerm);
//            }
//        });
//
//        // Observer to update views when the search results change
//
//        final Observer<List<AccountUser>> searchResultListObserver = new Observer<List<AccountUser>>() {
//            @Override
//            public void onChanged(@Nullable final List<AccountUser> searchResultList) {
//                int searchListVisibility = View.VISIBLE;
//                if(searchResultList.isEmpty()) {
//                    searchListVisibility = View.GONE;
//                }
//                searchListRecycler.setVisibility(searchListVisibility);
//                searchListAdapter.notifyDataSetChanged();
//            }
//        };
//
//        // Apply observer to search list LiveData instance
//
//        model.getSearchResultUsers().observe(getViewLifecycleOwner(), searchResultListObserver);
//    }
//
//    private static class SearchedUserClickListener implements AccountUserAdapter.OnUserClickListener {
//
//        private UserSelectorViewModel model;
//
//        public SearchedUserClickListener(UserSelectorViewModel model) {
//            this.model = model;
//        }
//
//        @Override
//        public void onUserClick(int position) {
//            model.addUserToSelection(position);
//        }
//
//    }
//
//}